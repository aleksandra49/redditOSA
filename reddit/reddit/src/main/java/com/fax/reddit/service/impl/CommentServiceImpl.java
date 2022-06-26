package com.fax.reddit.service.impl;

import com.fax.reddit.dto.*;
import com.fax.reddit.model.RComment;
import com.fax.reddit.model.RPost;
import com.fax.reddit.model.RUser;
import com.fax.reddit.repository.RCommentRepository;
import com.fax.reddit.repository.RCommunityRepository;
import com.fax.reddit.repository.RPostRepository;
import com.fax.reddit.repository.RUserRepository;
import com.fax.reddit.security.TokenUtils;
import com.fax.reddit.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    RCommentRepository rCommentRepository;

    @Autowired
    RPostRepository rPostRepository;

    @Autowired
    RPostRepository postRepository;

    @Autowired
    RUserRepository rUserRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public CommentResponse getAll(int postId) {

        CommentResponse response = new CommentResponse();

        List<RComment> comments = rCommentRepository.findAllByRPostId(postId);

        List<CommentRes> res = new ArrayList<>();
        for(RComment rc:comments){
            CommentRes tmp = new CommentRes();
            CommentChild main = new CommentChild();
            main.setAvatar(rc.getRUser().getAvatar());
            main.setCreator(rc.getRUser().getDisplayName());
            main.setId(rc.getId());
            main.setText(rc.getText());
            main.setTimestamp(rc.getTimestamp().toString());
            tmp.setMain(main);

            List<CommentChild> commentChildren = new ArrayList<>();
            for(RComment rcc:rc.getSubComments()){
                CommentChild child = new CommentChild();
                child.setAvatar(rcc.getRUser().getAvatar());
                child.setCreator(rcc.getRUser().getDisplayName());
                child.setId(rcc.getId());
                child.setText(rcc.getText());
                child.setTimestamp(rcc.getTimestamp().toString());
                commentChildren.add(child);
            }
            tmp.setAnswers(commentChildren);
            res.add(tmp);
        }


        RPost p = rPostRepository.getById(postId);
        PostRes pr = new PostRes();
        pr.setCreator(p.getRUser().getDisplayName());
        pr.setId(p.getId());
        pr.setImagePath(p.getImagePath());
        pr.setText(p.getText());

        response.setComments(res);
        response.setPost(pr);

        return response;
    }

    @Override
    public void create(CommentReq req, int postId, String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);

        RComment comment = new RComment();
        comment.setText(req.getText());
        comment.setRUser(rUser);

        if(req.getCommentId()>0){
            comment.setMainComment(rCommentRepository.getById(req.getCommentId()));
        } else if (postId > 0) {
            comment.setRPost(rPostRepository.getById(postId));
        }

        comment.setIsDeleted((byte) 0);
        comment.setTimestamp(new Date());
        rCommentRepository.save(comment);
    }
    

}

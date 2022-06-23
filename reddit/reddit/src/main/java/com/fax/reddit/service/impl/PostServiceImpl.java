package com.fax.reddit.service.impl;

import com.fax.reddit.dto.PostReq;
import com.fax.reddit.dto.PostRes;
import com.fax.reddit.enums.ReactionEnum;
import com.fax.reddit.model.RPost;
import com.fax.reddit.model.RReaction;
import com.fax.reddit.model.RUser;
import com.fax.reddit.repository.RCommunityRepository;
import com.fax.reddit.repository.RPostRepository;
import com.fax.reddit.repository.RReactionRepository;
import com.fax.reddit.repository.RUserRepository;
import com.fax.reddit.security.TokenUtils;
import com.fax.reddit.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    RPostRepository postRepository;

    @Autowired
    RUserRepository rUserRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    RCommunityRepository rCommunityRepository;

    @Autowired
    RReactionRepository rReactionRepository;

    @Override
    public List<PostRes> getAll(int communityId) {
        List<RPost> rPosts = postRepository.findAllByRCommunityId(communityId);
        List<PostRes> res = new ArrayList<>();
        for (RPost p : rPosts) {
            PostRes pr = new PostRes();
            pr.setCreator(p.getRUser().getDisplayName());
            pr.setId(p.getId());
            pr.setImagePath(p.getImagePath());
            pr.setText(p.getText());
            int upvotecount = 0;
            int downvotecount = 0;
            for(RReaction reaction:p.getRReactions()){
                if(reaction.getType().equals(ReactionEnum.UP.name())){
                    upvotecount++;
                }else{
                    downvotecount++;
                }
            }
            int charm = upvotecount - downvotecount;
            pr.setCharm(charm);
            res.add(pr);
        }

        return res;
    }

    @Override
    public void create(PostReq req, int communityId, String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        RPost pos = new RPost();
        pos.setText(req.getText());
        pos.setImagePath(req.getImagePath());
        pos.setRUser(rUser);
        pos.setRCommunity(rCommunityRepository.getById(communityId));
        postRepository.save(pos);
    }

    @Override
    public void upvote(int postId, String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        RPost rPost = postRepository.getById(postId);
        RReaction rReaction = rReactionRepository.findByRUserAndRPost(rUser, rPost);
        if(rReaction == null){
            rReaction = new RReaction();
        }
        rReaction.setRPost(rPost);
        rReaction.setType(ReactionEnum.UP.name());
        rReaction.setRUser(rUser);
        rReactionRepository.save(rReaction);
    }

    @Override
    public void downvote(int postId, String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        RPost rPost = postRepository.getById(postId);
        RReaction rReaction = rReactionRepository.findByRUserAndRPost(rUser, rPost);
        if(rReaction == null){
            rReaction = new RReaction();
        }
        rReaction.setRPost(rPost);
        rReaction.setType(ReactionEnum.DOWN.name());
        rReaction.setRUser(rUser);
        rReactionRepository.save(rReaction);
    }
}

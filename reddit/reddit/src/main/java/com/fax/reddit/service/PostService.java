package com.fax.reddit.service;

import com.fax.reddit.dto.PostReq;
import com.fax.reddit.dto.PostRes;

import java.util.List;

public interface PostService {
    List<PostRes> getAll(int communityId);

    void create(PostReq req, int communityId, String token);

    void upvote(int postId, String token);
    void downvote(int postId, String token);
    
    void remove(int id, String token);
}

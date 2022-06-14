package com.fax.reddit.service;

import com.fax.reddit.dto.CommentReq;
import com.fax.reddit.dto.CommentRes;
import com.fax.reddit.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse getAll(int postId);

    void create(CommentReq req, int postId, String token);
}

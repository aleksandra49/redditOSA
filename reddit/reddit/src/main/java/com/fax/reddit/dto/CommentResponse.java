package com.fax.reddit.dto;

import java.util.List;

public class CommentResponse {
    private PostRes post;

    private List<CommentRes> comments;

    public List<CommentRes> getComments() {
        return comments;
    }

    public void setComments(List<CommentRes> comments) {
        this.comments = comments;
    }

    public PostRes getPost() {
        return post;
    }

    public void setPost(PostRes post) {
        this.post = post;
    }
}

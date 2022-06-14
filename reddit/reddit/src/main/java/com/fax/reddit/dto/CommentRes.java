package com.fax.reddit.dto;

import java.util.List;

public class CommentRes {



    private CommentChild main;
    private List<CommentChild> answers;

    public CommentChild getMain() {
        return main;
    }

    public void setMain(CommentChild main) {
        this.main = main;
    }

    public List<CommentChild> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CommentChild> answers) {
        this.answers = answers;
    }


}

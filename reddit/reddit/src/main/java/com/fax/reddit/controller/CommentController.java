package com.fax.reddit.controller;

import com.fax.reddit.dto.*;
import com.fax.reddit.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam int postId) {
        logger.info("comment page loaded for comment {}",postId);
        CommentResponse response = null;
        try {
            response = commentService.getAll(postId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam int postId, @RequestBody CommentReq req, @RequestHeader("Authorization") String token) {
        logger.info("comment creation");
        try {
            commentService.create(req, postId, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam int id, @RequestHeader("Authorization") String token) {
        logger.info("removing comment {}",id);
        try {
        	commentService.remove(id, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

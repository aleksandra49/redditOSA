package com.fax.reddit.controller;

import com.fax.reddit.dto.*;
import com.fax.reddit.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam int communityId) {
        logger.info("post page loaded for community {}",communityId);
        List<PostRes> response = null;
        try {
            response = postService.getAll(communityId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam int communityId, @RequestBody PostReq req, @RequestHeader("Authorization") String token) {
        logger.info("Community creation");
        try {
            postService.create(req, communityId, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/upvote")
    public ResponseEntity<?> upvote(@RequestParam int postId, @RequestHeader("Authorization") String token) {
        logger.info("voting {}",postId);
        try {
            postService.upvote(postId, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/downvote")
    public ResponseEntity<?> downvote(@RequestParam int postId, @RequestHeader("Authorization") String token) {
        logger.info("voting {}",postId);
        try {
            postService.downvote(postId, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam int id, @RequestHeader("Authorization") String token) {
        logger.info("removing post {}",id);
        try {
        	postService.remove(id, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/getId")
    public ResponseEntity<?> getId(@RequestParam int postId) {
        logger.info("getting id");
        
        PostRes response = null;
        try {
             response = postService.getId(postId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/izmena")
    public ResponseEntity<?> izmena(@RequestParam int id, @RequestHeader("Authorization") String token, @RequestBody PostReq req) {
    	PostRes res = null;
        try {
            res = postService.izmena(id,req, token);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    
}

package com.fax.reddit.controller;

import com.fax.reddit.dto.CommunityReq;
import com.fax.reddit.dto.CommunityRes;
import com.fax.reddit.dto.ErrorDto;
import com.fax.reddit.dto.UpdateCommunityReq;
import com.fax.reddit.dto.UserReq;
import com.fax.reddit.dto.UserRes;
import com.fax.reddit.model.RCommunity;
import com.fax.reddit.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("community")
public class CommunityController {

    Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    CommunityService communityService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        logger.info("Index page loaded");
        List<CommunityRes> response = null;
        try {
            response = communityService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/getId")
    public ResponseEntity<?> getId(@RequestParam int communityId) {
        logger.info("getting id");
        //List<CommunityRes> response = null;
        //RCommunity response = null;
        CommunityRes response = null;
        try {
             //response = communityService.getAll(communityId);
             response = communityService.getId(communityId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommunityReq req, @RequestHeader("Authorization") String token) {
        logger.info("Community creation");
        try {
            communityService.create(req, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam int id, @RequestHeader("Authorization") String token) {
        logger.info("removing community {}",id);
        try {
        	communityService.remove(id, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/izmena")
    public ResponseEntity<?> izmena(@RequestParam int id, @RequestHeader("Authorization") String token, @RequestBody UpdateCommunityReq req) {
        CommunityRes res = null;
        try {
            res = communityService.izmena(id,req, token);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
    
}

package com.fax.reddit.controller;


import com.fax.reddit.dto.*;
import com.fax.reddit.model.RUser;
import com.fax.reddit.security.TokenUtils;
import com.fax.reddit.service.KorisnikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KorisnickiController {

    Logger logger = LoggerFactory.getLogger(KorisnickiController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("korisnikServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        logger.info("Pokusan login sa korisnikom {}", user.getUsername());
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
            RUser userDb = korisnikService.findByUsername(user.getUsername());
            LoggedInUserDTO loggedIn = new LoggedInUserDTO(userDb.getId(), tokenUtils.generisiToken(details),
                    details.getUsername(), details.getAuthorities());
            if (userDb.getRBanneds().size() > 0) {
                return new ResponseEntity<>(new ErrorDto("Vas nalog je deaktiviran!"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(loggedIn, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Nevalidni podaci");
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto("Nevalidni podaci"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registrujKorisnika")
    public ResponseEntity<?> registrujKorisnika(@RequestBody UserReq req) {
        logger.info("Registruje se korisnik {}", req.getUsername());
        UserRes res = null;
        try {
            res = korisnikService.registrujKorisnika(req);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/izmena")
    public ResponseEntity<?> izmena(@RequestHeader("Authorization") String token, @RequestBody UserReq req) {
        UserRes res = null;
        try {
            res = korisnikService.izmena(req, token);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/myProfile")
    public ResponseEntity<?> myProfile(@RequestHeader("Authorization") String token) {
        UserRes res = null;
        try {
            res = korisnikService.myProfile(token);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

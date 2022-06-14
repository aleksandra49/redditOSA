package com.fax.reddit.service;


import com.fax.reddit.dto.UserReq;
import com.fax.reddit.dto.UserRes;
import com.fax.reddit.model.RUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface KorisnikService extends UserDetailsService {
    RUser findByUsername(String username);

    UserRes registrujKorisnika(UserReq req) throws Exception;

    UserRes myProfile(String token) throws Exception;

    UserRes izmena(UserReq req, String token) throws Exception;
}

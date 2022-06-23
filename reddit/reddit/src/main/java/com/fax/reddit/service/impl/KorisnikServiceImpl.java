package com.fax.reddit.service.impl;

import com.fax.reddit.dto.UserReq;
import com.fax.reddit.dto.UserRes;
import com.fax.reddit.model.RUser;
import com.fax.reddit.repository.RUserRepository;
import com.fax.reddit.security.SecurityConfiguration;
import com.fax.reddit.security.TokenUtils;
import com.fax.reddit.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private final static String USER_ROLE = "USER";
    private final static String ADMIN_ROLE = "ADMIN";

    @Autowired
    RUserRepository rUserRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    SecurityConfiguration configuration;


    @Override
    public RUser findByUsername(String username) {
        return rUserRepository.findByUsername(username);
    }

    @Override
    public UserRes registrujKorisnika(UserReq req) throws Exception {
        RUser rUser = new RUser();
        rUser.setAvatar(req.getAvatar());
        rUser.setDescription(req.getDescription());
        rUser.setUsername(req.getUsername());
        rUser.setRole(USER_ROLE);
        rUser.setPassword(configuration.passwordEncoder().encode(req.getPassword()));
        rUser.setDisplayName(req.getDisplayName());
        rUser.setRegistrationDate(new Date());
        rUserRepository.save(rUser);
        return getUserRes(rUser);
    }

    @Override
    public UserRes myProfile(String token) throws Exception {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        return getUserRes(rUser);
    }


    @Override
    public UserRes izmena(UserReq req, String token) throws Exception {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);

        if(req.getOldPassword()!=null && !req.getOldPassword().isEmpty()) {
            if (!configuration.passwordEncoder().matches(req.getOldPassword(), rUser.getPassword())) {
                throw new Exception("Pogresna stara lozinka!");
            }
            if (req.getPassword() == null || req.getPasswordRepeat() == null || !req.getPassword().equals(req.getPasswordRepeat())) {
                throw new Exception("Passwordi se ne podudaraju");
            }
            rUser.setPassword(configuration.passwordEncoder().encode(req.getPassword()));
        }

        rUser.setAvatar(req.getAvatar());
        rUser.setDescription(req.getDescription());
        rUser.setUsername(req.getUsername());
        rUser.setDisplayName(req.getDisplayName());
        rUserRepository.save(rUser);
        return getUserRes(rUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RUser rUser = rUserRepository.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(rUser.getRole()));
        return new org.springframework.security.core.userdetails.User(rUser.getUsername(), rUser.getPassword(), grantedAuthorities);
    }

    private UserRes getUserRes(RUser rUser) {
        UserRes res = new UserRes();

        res.setAvatar(rUser.getAvatar());
        res.setDescription(rUser.getDescription());
        res.setUsername(rUser.getUsername());
        res.setRole(rUser.getRole());
        res.setDisplayName(rUser.getDisplayName());
        res.setId(rUser.getId());

        return res;
    }
}

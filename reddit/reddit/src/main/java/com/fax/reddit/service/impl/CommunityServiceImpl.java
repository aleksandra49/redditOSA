package com.fax.reddit.service.impl;

import com.fax.reddit.dto.CommunityReq;
import com.fax.reddit.dto.CommunityRes;
import com.fax.reddit.model.RCommunity;
import com.fax.reddit.model.RUser;
import com.fax.reddit.repository.RCommunityRepository;
import com.fax.reddit.repository.RUserRepository;
import com.fax.reddit.security.TokenUtils;
import com.fax.reddit.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    RCommunityRepository rCommunityRepository;

    @Autowired
    RUserRepository rUserRepository;

    @Autowired
    TokenUtils tokenUtils;
    @Override
    public List<CommunityRes> getAll() {
        List<RCommunity> all = rCommunityRepository.findAll();
        List<CommunityRes> res = new ArrayList<>();
        for(RCommunity c:all){
            CommunityRes cr = new CommunityRes();
            cr.setId(c.getId());
            cr.setDescription(c.getDescription());
            cr.setCreationDate(c.getCreationDate());
            cr.setCreator(c.getRUser().getDisplayName());
            cr.setIsSuspended(c.getIsSuspended());
            cr.setSuspendedReason(c.getSuspendedReason());
            res.add(cr);
        }
        return res;
    }

    @Override
    public void create(CommunityReq req, String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        RCommunity com = new RCommunity();
        com.setCreationDate(new Date());
        com.setDescription(req.getDescription());
        com.setIsSuspended((byte) 0);
        com.setRUser(rUser);
        rCommunityRepository.save(com);
    }
}

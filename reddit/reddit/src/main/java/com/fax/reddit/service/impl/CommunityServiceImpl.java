package com.fax.reddit.service.impl;

import com.fax.reddit.dto.CommunityReq;
import com.fax.reddit.dto.CommunityRes;
import com.fax.reddit.dto.PostRes;
import com.fax.reddit.dto.UpdateCommunityReq;
import com.fax.reddit.dto.UserReq;
import com.fax.reddit.dto.UserRes;
import com.fax.reddit.model.RCommunity;
import com.fax.reddit.model.RPost;
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
        	if(c.getCreationDate() == null) {
        		continue;
        	}
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

	@Override
	public void remove(int id, String token) {
		 String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
	     RUser rUser = rUserRepository.findByUsername(username);
	     RCommunity com = rCommunityRepository.getById(id);
	     com.setCreationDate(null); //logical remove
	     rCommunityRepository.save(com);
	}
	
	@Override
    public CommunityRes izmena(int communityId, UpdateCommunityReq req, String token) throws Exception {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        RUser rUser = rUserRepository.findByUsername(username);
        RCommunity rCommunity = rCommunityRepository.getById(communityId);

        rCommunity.setCreationDate(req.getCreationDate());
        rCommunity.setDescription(req.getDescription());
        //rCommunity.setIsSuspended(req.getIsSuspended());
        
        rCommunityRepository.save(rCommunity);
        return getCommunityRes(rCommunity);
    }
	
	//CommunityRes
	@Override 
	public CommunityRes getId(int communityId) {
		//RCommunity rPosts = rCommunityRepository.getById(communityId);
		RCommunity rCommunitis = rCommunityRepository.findById(communityId);
		//return rCommunityRepository.findById(communityId);
		//return rCommunitis;
		return getCommunityRes(rCommunitis);
	}
	
	
	private CommunityRes getCommunityRes(RCommunity rCommu) {
		CommunityRes res = new CommunityRes();

        res.setCreationDate(rCommu.getCreationDate());
        res.setDescription(rCommu.getDescription());
        res.setIsSuspended(rCommu.getIsSuspended());
        res.setSuspendedReason(rCommu.getSuspendedReason());
        //res.setCreator(rCommu.getCreator());
        res.setId(rCommu.getId());

        return res;
    }

	/*@Override
	public List<CommunityRes> getAll(int communityId) {
		List<RCommunity> rPosts = (List<RCommunity>) rCommunityRepository.findById(communityId);
		List<CommunityRes> res = new ArrayList<>();
		return res;
	}*/

	
	
	 
}

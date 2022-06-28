package com.fax.reddit.service;

import com.fax.reddit.dto.CommunityReq;
import com.fax.reddit.dto.CommunityRes;
import com.fax.reddit.dto.UpdateCommunityReq;
import com.fax.reddit.dto.UserReq;
import com.fax.reddit.dto.UserRes;
import com.fax.reddit.model.RCommunity;
import com.fax.reddit.model.RUser;

import java.util.List;

public interface CommunityService {
    List<CommunityRes> getAll();
    //List<CommunityRes> getAll(int communityId);
    
    CommunityRes getId(int id);
    //RCommunity getId(int id);

    void create(CommunityReq req, String token);

	void remove(int id, String token);
	
	//RCommunity findByIdd(int id);
	
	CommunityRes izmena(int communityId, UpdateCommunityReq req, String token) throws Exception;
	
	//RCommunity izmena(RCommunity rCommunity);
	//RCommunity save(RCommunity rCommunity);
}

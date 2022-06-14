package com.fax.reddit.service;

import com.fax.reddit.dto.CommunityReq;
import com.fax.reddit.dto.CommunityRes;

import java.util.List;

public interface CommunityService {
    List<CommunityRes> getAll();

    void create(CommunityReq req, String token);
}

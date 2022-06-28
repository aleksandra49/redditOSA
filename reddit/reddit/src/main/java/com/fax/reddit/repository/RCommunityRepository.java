package com.fax.reddit.repository;

import com.fax.reddit.dto.CommunityRes;
import com.fax.reddit.model.RCommunity;
import com.fax.reddit.model.RUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RCommunityRepository extends JpaRepository<RCommunity,Integer> {
	//CommunityRes findById(int id);
	RCommunity findById(int id);
}

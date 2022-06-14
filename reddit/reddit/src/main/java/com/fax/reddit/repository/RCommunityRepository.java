package com.fax.reddit.repository;

import com.fax.reddit.model.RCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RCommunityRepository extends JpaRepository<RCommunity,Integer> {
}

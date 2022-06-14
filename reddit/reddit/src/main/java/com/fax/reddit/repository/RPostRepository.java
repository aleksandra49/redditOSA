package com.fax.reddit.repository;

import com.fax.reddit.model.RPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RPostRepository extends JpaRepository<RPost,Integer> {
    List<RPost> findAllByRCommunityId(int id);
}

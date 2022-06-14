package com.fax.reddit.repository;

import com.fax.reddit.model.RPost;
import com.fax.reddit.model.RReaction;
import com.fax.reddit.model.RUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RReactionRepository extends JpaRepository<RReaction,Integer> {
    RReaction findByRUserAndRPost(RUser user, RPost post);
}

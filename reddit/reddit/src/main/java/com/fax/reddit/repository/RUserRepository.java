package com.fax.reddit.repository;

import com.fax.reddit.model.RUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RUserRepository extends JpaRepository<RUser, Integer> {
    RUser findByUsername(String username);
}

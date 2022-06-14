package com.fax.reddit.repository;

import com.fax.reddit.model.RComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RCommentRepository extends JpaRepository<RComment,Integer> {
    List<RComment> findAllByRPostId(int id);
}

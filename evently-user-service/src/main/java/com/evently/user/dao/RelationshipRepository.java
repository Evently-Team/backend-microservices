package com.evently.user.dao;

import com.evently.user.entity.Relationship;
import com.evently.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, String> {

    List<Relationship> findByUser(User user, Pageable pageable);
}

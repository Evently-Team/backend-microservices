package com.evently.user.dao.persistent;

import com.evently.user.entity.persistent.Relationship;
import com.evently.user.entity.persistent.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, String> {

    List<Relationship> findByUser(User user, Pageable pageable);
}

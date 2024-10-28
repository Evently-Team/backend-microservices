package com.evently.user.dao;

import com.evently.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select exists (select 1 from users u where u.username = :username)",
            nativeQuery = true)
    boolean existsByUsernameIncludingInactive(@Param("username") String username);
}

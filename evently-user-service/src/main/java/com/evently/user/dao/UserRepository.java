package com.evently.user.dao;

import com.evently.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select exists (select 1 from users u where u.username = :username)",
            nativeQuery = true)
    boolean existsByUsernameIncludingDeletedAccounts(@Param("username") String username);

    @Query(value = "select exists (select 1 from users u where u.email = :email)",
            nativeQuery = true)
    boolean existsByEmailIncludingDeletedAccounts(@Param("email") String email);

    Optional<User> findByUsername(String username);

    @Query(value = "select * from users u where u.username = :usernameOrEmail " +
            "or u.email = :usernameOrEmail and u.is_deleted = false",
            nativeQuery = true)
    Optional<User> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}

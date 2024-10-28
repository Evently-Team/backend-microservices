package com.evently.user.dao;

import com.evently.user.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {

    boolean existsByEmail(String email);
}

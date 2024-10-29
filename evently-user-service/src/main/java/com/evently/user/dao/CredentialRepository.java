package com.evently.user.dao;

import com.evently.user.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, String> {

    boolean existsByEmail(String email);
}

package com.evently.user.dao.persistent;

import com.evently.user.entity.persistent.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, String> {

    boolean existsByEmail(String email);
}

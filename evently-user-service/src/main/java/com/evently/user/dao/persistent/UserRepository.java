package com.evently.user.dao.persistent;

import com.evently.user.entity.persistent.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "select exists (select 1 from users u where u.username = :username)",
            nativeQuery = true)
    boolean existsByUsernameIncludingInactive(@Param("username") String username);
}

package com.evently.user.dao.cache;

import com.evently.user.entity.cache.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface RegistrationRepository extends CrudRepository<Registration, UUID> {

}

package com.brainmatics.data.repos;

import java.util.Optional;

import com.brainmatics.data.entity.UserApp;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserApp, Long>{
    
    Optional<UserApp> findByEmail(String email); 
}

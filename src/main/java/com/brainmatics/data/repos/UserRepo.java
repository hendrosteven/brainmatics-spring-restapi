package com.brainmatics.data.repos;

import java.util.Optional;

import com.brainmatics.data.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long>{
    
    Optional<User> findByEmail(String email);
}

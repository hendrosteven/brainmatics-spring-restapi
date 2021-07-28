package com.brainmatics.data.repos;

import com.brainmatics.data.entity.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    
}

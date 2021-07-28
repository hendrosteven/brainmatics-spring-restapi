package com.brainmatics.data.repos;

import com.brainmatics.data.entity.Category;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {
    
}

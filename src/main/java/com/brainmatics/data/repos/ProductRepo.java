package com.brainmatics.data.repos;

import java.util.List;

import com.brainmatics.data.entity.Product;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
    
    public List<Product> findByCategoryId(Long id);
    
}

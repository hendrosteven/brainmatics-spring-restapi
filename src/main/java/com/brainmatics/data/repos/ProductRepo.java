package com.brainmatics.data.repos;

import java.util.List;

import com.brainmatics.data.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
    
    public List<Product> findByCategoryId(Long id);
    
}

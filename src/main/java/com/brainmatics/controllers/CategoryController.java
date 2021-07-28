package com.brainmatics.controllers;

import com.brainmatics.data.entity.Category;
import com.brainmatics.data.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    @Autowired
    private CategoryRepo repo;

    @GetMapping
    public Iterable<Category> findAll(){
        return repo.findAll();
    }

    @PostMapping
    public Category createOne(@RequestBody Category category){
        return repo.save(category);
    }
}

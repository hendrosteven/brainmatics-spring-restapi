package com.brainmatics.controllers;

import java.util.Optional;

import com.brainmatics.data.entity.Category;
import com.brainmatics.data.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        Optional<Category> category = repo.findById(id);
        if(!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    @PutMapping
    public Category updateOne(@RequestBody Category category){
        return repo.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id){
        try{
            repo.deleteById(id);
        }catch(Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}

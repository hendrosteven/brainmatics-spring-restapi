package com.brainmatics.controllers;

import java.util.Optional;

import com.brainmatics.data.entity.Category;
import com.brainmatics.data.repos.CategoryRepo;
import com.brainmatics.dto.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page,@PathVariable("size") int size){
        ResponseData<Iterable<Category>> response = new ResponseData<>();
        try{
            Pageable pageable = PageRequest.of(page, size);
            response.setPlayload(repo.findAll(pageable));
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.setPlayload(null);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody Category category){
        ResponseData<Category> response = new ResponseData<>();
        try{
            response.setPlayload(repo.save(category));
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.setPlayload(null);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }        
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        ResponseData<Category> response = new ResponseData<>();
        Optional<Category> category = repo.findById(id);
        if(!category.isPresent()) {
            response.setPlayload(null);
            response.setStatus(false);
            response.getMessages().add("Id not found");            
        }else{
            response.setPlayload(category.get());
            response.setStatus(true);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateOne(@RequestBody Category category){
        ResponseData<Category> response = new ResponseData<>();
        try{
            response.setPlayload(repo.save(category));
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.setPlayload(null);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long id){
        ResponseData<?> response = new ResponseData<>();
        try{
            repo.deleteById(id);
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.setPlayload(null);
            response.getMessages().add("Failed to delete the data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

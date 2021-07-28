package com.brainmatics.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.brainmatics.data.entity.Product;
import com.brainmatics.data.repos.CategoryRepo;
import com.brainmatics.data.repos.ProductRepo;
import com.brainmatics.dto.ProductData;
import com.brainmatics.dto.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseData<Iterable<Product>> response = new ResponseData<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            response.setPlayload(repo.findAll(pageable));
            response.setStatus(true);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            response.setStatus(false);
            response.setPlayload(null);
            response.getMessages().add(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(@Valid @RequestBody ProductData productData, Errors errors) {
        ResponseData<Product> response = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessages().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Product product = new Product();
        product.setCode(productData.getCode());
        product.setName(productData.getName());
        product.setPrice(productData.getPrice());
        product.setDescription(productData.getDescription());
        product.setCategory(categoryRepo.findById(productData.getCategoryId()).get());

        response.setPlayload(repo.save(product));
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        ResponseData<Product> response = new ResponseData<>();
        Optional<Product> product = repo.findById(id);
        if (!product.isPresent()) {
            response.setPlayload(null);
            response.setStatus(false);
            response.getMessages().add("Id not found");
        } else {
            response.setPlayload(product.get());
            response.setStatus(true);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable("id") Long id, @RequestBody ProductData productData) {
        ResponseData<Product> response = new ResponseData<>();
        try {
            Product product = repo.findById(id).get();
            product.setCode(productData.getCode());
            product.setName(productData.getName());
            product.setPrice(productData.getPrice());
            product.setDescription(productData.getDescription());
            product.setCategory(categoryRepo.findById(productData.getCategoryId()).get());
            response.setPlayload(repo.save(product));
            response.setStatus(true);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
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

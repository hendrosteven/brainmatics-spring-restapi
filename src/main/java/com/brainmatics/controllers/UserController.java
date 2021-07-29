package com.brainmatics.controllers;

import javax.validation.Valid;

import com.brainmatics.data.entity.UserApp;
import com.brainmatics.dto.ResponseData;
import com.brainmatics.dto.UserData;
import com.brainmatics.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserData userData, Errors errors){
        ResponseData<UserApp> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessages().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserApp user = modelMapper.map(userData, UserApp.class);
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        response.setPlayload(userService.createOne(user));
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }

    
}

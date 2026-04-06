package com.finance.dashboard.controller;

import com.finance.dashboard.dto.ApiResponse;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public ResponseEntity<ApiResponse<String>> healthCheck() {

        ApiResponse<String> response = new ApiResponse<>(
                "SUCCESS",
                "Service is up",
                "OK"
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user){

        User savedUser = userService.createUser(user);

        ApiResponse<User> response = new ApiResponse<>(
                "SUCCESS",
                "User created successfully",
                savedUser
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){

        List<User> users = userService.getAllUsers();

        ApiResponse<List<User>> response = new ApiResponse<>(
                "SUCCESS",
                "Users fetched successfully",
                users
        );

        return ResponseEntity.ok(response);
    }
}
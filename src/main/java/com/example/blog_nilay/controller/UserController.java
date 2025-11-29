package com.example.blog_nilay.controller;

import com.example.blog_nilay.entity.User;
import com.example.blog_nilay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to the Blog API! Go to /h2-console for the DB.";
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}

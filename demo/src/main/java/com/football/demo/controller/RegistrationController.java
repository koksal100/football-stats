package com.football.demo.controller;

import com.football.demo.security.User;
import com.football.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user) {
        try {
            userService.registerNewUser(user.getUsername(), user.getPassword());
            return "User registered successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register/admin")
    public String registerAdmin(@Valid @RequestBody User user) {
        try {
            userService.registerUserWithRole(user.getUsername(), user.getPassword(), "ROLE_ADMIN");
            return "Admin registered successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}

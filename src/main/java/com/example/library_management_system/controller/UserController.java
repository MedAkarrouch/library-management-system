package com.example.library_management_system.controller;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){this.userService=userService;}
    @GetMapping("/me")
    public ResponseEntity<User> getMe(Principal principal){
        User user = userService.getByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }
}

package com.example.library_management_system.controller;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){this.userService=userService;}
    @GetMapping("/me")
    public ResponseEntity<User> getMe(HttpServletRequest request){
        return ResponseEntity.ok((User) request.getAttribute("loggedInUser"));
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}

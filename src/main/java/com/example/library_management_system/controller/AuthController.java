package com.example.library_management_system.controller;

import com.example.library_management_system.body.request.LoginUserRequest;
import com.example.library_management_system.body.request.RegisterUserRequest;
import com.example.library_management_system.body.response.LoginUserResponse;
import com.example.library_management_system.body.response.RegisterUserResponse;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.service.AuthService;
import com.example.library_management_system.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private JwtService jwtService;
    @Autowired
    public AuthController(AuthService authService,JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request){
        User user = authService.login(request);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginUserResponse(token,user));
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody @Valid RegisterUserRequest request){
        User user = authService.register(request);
        String token = jwtService.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterUserResponse(token,user));
    }
}

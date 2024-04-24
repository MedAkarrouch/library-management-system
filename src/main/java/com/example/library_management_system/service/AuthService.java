package com.example.library_management_system.service;

import com.example.library_management_system.body.request.LoginUserRequest;
import com.example.library_management_system.body.request.RegisterUserRequest;
import com.example.library_management_system.entity.ERole;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.exception.NotFountException;
import com.example.library_management_system.repository.RoleRepository;
import com.example.library_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository,RoleRepository roleRepository
            ,AuthenticationManager authenticationManager , PasswordEncoder passwordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    public  User login(LoginUserRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        return userRepository.findByEmail(request.getEmail()).
                orElseThrow(()->new RuntimeException("User not found"));
    }
    public User register(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new NotFountException("This email is already taken, Please use another email");
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        user.add(roleRepository.findByName(ERole.ROLE_ADMIN));
        return userRepository.save(user);
    }
}

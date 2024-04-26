package com.example.library_management_system.service;

import com.example.library_management_system.body.request.LoginUserRequest;
import com.example.library_management_system.body.request.RegisterUserRequest;
import com.example.library_management_system.entity.ERole;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.exception.InvalidEmailOrPasswordException;
import com.example.library_management_system.exception.NotFountException;
import com.example.library_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository ,AuthenticationManager authenticationManager , PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    public  User login(LoginUserRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
            );
        }catch(Exception e){
            System.out.print(e);
            throw new InvalidEmailOrPasswordException();
        }
        return userRepository.findByEmail(request.getEmail()).
                orElseThrow(()->new NotFountException("User not found"));
    }
    public User register(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new NotFountException("This email is already taken, Please use another email");
        User user = User.builder().email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
//                .role(ERole.ROLE_ADMIN)
                .role(ERole.ROLE_USER)
                .build();
        return userRepository.save(user);
    }
}

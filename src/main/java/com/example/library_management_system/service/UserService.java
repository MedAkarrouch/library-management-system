package com.example.library_management_system.service;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){this.userRepository=userRepository;}
    public User getByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
    }

}

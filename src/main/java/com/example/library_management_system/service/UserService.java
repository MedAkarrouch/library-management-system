package com.example.library_management_system.service;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.exception.NotFountException;
import com.example.library_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){this.userRepository=userRepository;}
    public User getByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
//    public void disableUserAccountById(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(()->new NotFountException("User not found"));
//        user.setEnabled(false);
//        userRepository.save(user);
//    }
//    public void enableUserAccountById(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(()->new NotFountException("User not found"));
//        user.setEnabled(true);
//        userRepository.save(user);
//    }

}

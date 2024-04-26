package com.example.library_management_system.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(){
        super("Invalid email or password");
    }
}

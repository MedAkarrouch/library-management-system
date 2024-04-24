package com.example.library_management_system.exception;

import org.springframework.stereotype.Component;

public class NotFountException extends RuntimeException{
    public NotFountException(String message){
        super(message);
    }
}

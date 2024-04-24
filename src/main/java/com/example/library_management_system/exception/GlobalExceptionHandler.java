package com.example.library_management_system.exception;

import com.example.library_management_system.body.response.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.library_management_system.body.response.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleException(MethodArgumentNotValidException exc){
       Map<String,String> errors = new HashMap<>();
       exc.getBindingResult().getAllErrors().forEach(error->
               errors.put(((FieldError) error).getField(),error.getDefaultMessage()));
       ValidationResponse response = ValidationResponse.
               builder().status(HttpStatus.BAD_REQUEST.value())
               .errors(errors).build();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFountException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        return new ResponseEntity(error,HttpStatus.NOT_FOUND);
    }
}

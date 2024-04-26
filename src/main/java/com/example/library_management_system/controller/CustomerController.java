package com.example.library_management_system.controller;

import com.example.library_management_system.body.request.StoreCustomerRequest;
import com.example.library_management_system.entity.Customer;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    final private CustomerService customerService;
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        List<Customer> customers = customerService.getCustomers(user);
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Customer customer = customerService.getCustomer(customerId,user);
        return ResponseEntity.ok(customer);
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody StoreCustomerRequest requestBody , HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Customer customer = customerService.addCustomer(requestBody,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        customerService.deleteCustomer(customerId,user);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Customer updatedCustomer = customerService.updateCustomer(customer,user);
        return ResponseEntity.ok(updatedCustomer);
    }
}

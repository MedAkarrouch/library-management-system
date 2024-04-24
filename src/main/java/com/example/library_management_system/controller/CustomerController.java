package com.example.library_management_system.controller;

import com.example.library_management_system.body.request.StoreCustomerRequest;
import com.example.library_management_system.entity.Customer;
import com.example.library_management_system.service.CustomerService;
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
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId){
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody StoreCustomerRequest request){
        Customer customer = customerService.saveCustomer(new Customer(
                request.getName(),request.getEmail(),request.getPhoneNumber()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
}

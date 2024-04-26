package com.example.library_management_system.service;

import com.example.library_management_system.body.request.StoreCustomerRequest;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.entity.Customer;
import com.example.library_management_system.exception.NotFountException;
import com.example.library_management_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final private CustomerRepository customerRepository;
    public Customer addCustomer(StoreCustomerRequest request, User user){
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .user(user).build();
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers(User user){
        return customerRepository.findAllByUserId(user.getId());
    }
    public Customer getCustomer(Long customerId,User user){
        return customerRepository.findByIdAndUserId(customerId,user.getId()).
                orElseThrow(()->new NotFountException("Customer not found"));
    }
    public void deleteCustomer(Long customerId,User user){
        Customer customer = getCustomer(customerId,user);
        customerRepository.delete(customer);
    }
    public Customer updateCustomer(Customer customer,User user){
        if(!customerRepository.existsByIdAndUserId(customer.getId(),user.getId()))
            throw new NotFountException("Customer not found");
        customer.setUser(user);
        return customerRepository.save(customer);
    }
}

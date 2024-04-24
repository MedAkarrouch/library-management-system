package com.example.library_management_system.service;

import com.example.library_management_system.entity.Customer;
import com.example.library_management_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final private CustomerRepository customerRepository;
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).
                orElseThrow(()->new RuntimeException("Customer not found with id = "+id));
    }
    public void deleteCustomer(Long id){
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer){
        if(!customerRepository.existsById(customer.getId()))
            throw new RuntimeException("Customer not found with id = "+customer.getId());
        return saveCustomer(customer);
    }
}

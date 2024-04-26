package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsById(Long id);

    List<Customer> findAllByUserId(Long userId);

    Optional<Customer> findByIdAndUserId(Long customerId,Long userId);
    boolean existsByIdAndUserId(Long customerId,Long userId);
}

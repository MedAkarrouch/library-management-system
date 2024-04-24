package com.example.library_management_system.repository;

import com.example.library_management_system.entity.ERole;
import com.example.library_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);
}

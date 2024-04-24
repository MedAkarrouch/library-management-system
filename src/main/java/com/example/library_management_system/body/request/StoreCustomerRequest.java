package com.example.library_management_system.body.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreCustomerRequest {
    private String name;
    private String email;
    private String phoneNumber;
}

package org.example.customerservice.Service;

import org.example.customerservice.Models.Dtos.CustomerDtoSave;
import org.example.customerservice.Models.Entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer save(CustomerDtoSave customerDtoSave);
    Optional<Customer> findById(UUID id);
}

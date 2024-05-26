package org.example.customerservice.Service.ServiceImp;

import org.example.customerservice.Models.Dtos.CustomerDtoSave;
import org.example.customerservice.Models.Entities.Customer;
import org.example.customerservice.Models.Mapper.CustomerMapper;
import org.example.customerservice.Repository.CustomerRepository;
import org.example.customerservice.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImp(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public Customer save(CustomerDtoSave customerDtoSave) {
        return customerRepository.save(customerMapper.toCustomer(customerDtoSave));
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::toCustomerDtoSend);
    }
}

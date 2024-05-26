package org.example.customerservice.Models.Mapper;

import org.example.customerservice.Models.Dtos.CustomerDtoSave;
import org.example.customerservice.Models.Entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDtoSave customerDtoSave);
    CustomerDtoSave toCustomerDtoSave(Customer customer);
    Customer toCustomerDtoSend(Customer customer);
}
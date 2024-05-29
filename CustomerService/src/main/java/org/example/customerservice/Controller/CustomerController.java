package org.example.customerservice.Controller;

import jakarta.validation.Valid;
import org.example.customerservice.Models.Dtos.CustomerDtoSave;
import org.example.customerservice.Models.Entities.Customer;
import org.example.customerservice.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDtoSave customerDtoSave){
        return ResponseEntity.created(URI.create("/micro/customer"))
                .body(customerService.save(customerDtoSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(customerService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found")));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

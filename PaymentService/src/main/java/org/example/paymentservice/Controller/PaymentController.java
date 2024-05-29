package org.example.paymentservice.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.paymentservice.Models.Dtos.PaymentDtoSave;
import org.example.paymentservice.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PaymentDtoSave paymentDtoSave) {
        try {
            return ResponseEntity.ok(paymentService.guardar(paymentDtoSave));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(paymentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

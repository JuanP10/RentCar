package org.example.paymentservice.Service;

import org.example.paymentservice.Models.Dtos.PaymentDtoSave;
import org.example.paymentservice.Models.Dtos.PaymentDtoSend;

import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    PaymentDtoSend guardar (PaymentDtoSave paymentDtoSave);
    Optional<PaymentDtoSend> findById(UUID id);
}

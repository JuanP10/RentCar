package org.example.paymentservice.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PaymentDtoSend {
    private UUID idPayment;
    private UUID idBooking;
    private Long creditCardNumber;
    private Double amount;
    private String status;
    private UUID transactionId;
}

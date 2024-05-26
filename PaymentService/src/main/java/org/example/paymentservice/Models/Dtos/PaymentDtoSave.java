package org.example.paymentservice.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDtoSave {
    private UUID idBooking;
    private Long creditCardNumber;
    private Double amount;
}

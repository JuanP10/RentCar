package org.example.paymentservice.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@Table(name = "payments")
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID bookingId;
    private Long creditCardNumber;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEstatus status;
    private UUID transactionId;
    public enum PaymentEstatus{
        COMPLETED,
        CANCELED,
        FAILED
    }
}

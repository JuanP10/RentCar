package org.example.paymentservice.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class BookingRequest {
    private UUID idBooking;
    private UUID idCustomer;
    private UUID idCar;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}

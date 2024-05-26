package org.example.bookingservice.Models.Dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookingservice.Models.Entities.Booking;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDtoSend {
    private UUID id;
    private UUID customerId;
    @Enumerated(EnumType.STRING)
    private Booking.BookingStatus status;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;

}

package org.example.bookingservice.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Bookings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID carId;
    private UUID customerId;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private LocalDate startDate;
    private LocalDate endDate;


    public enum BookingStatus {
        CONFIRMED,
        CANCELLED,
        COMPLETE,
        FAILED
    }
}


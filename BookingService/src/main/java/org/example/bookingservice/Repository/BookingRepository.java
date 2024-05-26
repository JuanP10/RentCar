package org.example.bookingservice.Repository;

import org.example.bookingservice.Models.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}

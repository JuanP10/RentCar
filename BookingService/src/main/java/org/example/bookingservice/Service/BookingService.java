package org.example.bookingservice.Service;

import org.example.bookingservice.Models.Dtos.BookingDtoSave;
import org.example.bookingservice.Models.Dtos.BookingDtoSend;

import java.util.UUID;

public interface BookingService {
    BookingDtoSend createBooking (BookingDtoSave bookingDtoSave);
    BookingDtoSend getBookingById(UUID id);
    String CONFIRMED (UUID id);
    String CANCELLED (UUID id);
    String COMPLETED (UUID id);
    String FAILED (UUID id);
}

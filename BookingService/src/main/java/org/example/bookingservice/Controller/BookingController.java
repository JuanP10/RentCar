package org.example.bookingservice.Controller;

import org.example.bookingservice.Models.Dtos.BookingDtoSave;
import org.example.bookingservice.Models.Entities.Booking;
import org.example.bookingservice.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking (@RequestBody BookingDtoSave bookingDtoSave) {
        try {
            return ResponseEntity.ok(bookingService.createBooking(bookingDtoSave));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.getBookingById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> CANCELLED (@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.CANCELLED(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/confirmado/{id}")
    public ResponseEntity<?> CONFIRMED (@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.CONFIRMED(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/completar/{id}")
    public ResponseEntity<?> COMPLETED (@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.COMPLETED(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/fail/{id}")
    public ResponseEntity<?> FAILED (@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.FAILED(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancelado/{id}")
    public ResponseEntity<?> DELETE (@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.CANCELLED(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

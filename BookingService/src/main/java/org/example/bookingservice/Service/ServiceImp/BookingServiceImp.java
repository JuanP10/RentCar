package org.example.bookingservice.Service.ServiceImp;

import org.example.bookingservice.Models.Dtos.BookingDtoSave;
import org.example.bookingservice.Models.Dtos.BookingDtoSend;
import org.example.bookingservice.Models.Dtos.CarDto;
import org.example.bookingservice.Models.Dtos.CustomerDto;
import org.example.bookingservice.Models.Entities.Booking;
import org.example.bookingservice.Models.Mappers.BookingMapper;
import org.example.bookingservice.Repository.BookingRepository;
import org.example.bookingservice.Service.BookingService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.UUID;

@Service
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RestTemplate restTemplate;

    public BookingServiceImp(BookingRepository bookingRepository, BookingMapper bookingMapper, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.restTemplate = restTemplate;
    }
    @Override
    public BookingDtoSend createBooking(BookingDtoSave bookingDtoSave) {
        try {
            // Construir URLs
            String urlCustomer = "http://container2-app:8080/microservice/1.0.0/customers/" + bookingDtoSave.getCustomerId();
            String urlCar = "http://container1-app:8080/microservice/1.0.0/carrepository/" + bookingDtoSave.getCarId();

            // Realizar solicitudes REST
            CustomerDto customerDto = restTemplate.getForObject(urlCustomer, CustomerDto.class);
            CarDto carDto = restTemplate.exchange(urlCar, HttpMethod.PUT, null, CarDto.class).getBody();

            // Validar respuestas
            if (customerDto != null && carDto != null) {
                Booking booking = bookingMapper.toEntity(bookingDtoSave);
                booking.setStatus(Booking.BookingStatus.CONFIRMED);
                booking.setStartDate(LocalDate.now());
                return bookingMapper.toDtoSend(bookingRepository.save(booking));
            } else {
                throw new RuntimeException("Failed to confirm customer or car availability");
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("External service call failed: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error saving booking: " + ex.getMessage(), ex);
        }
    }


    @Override
    public BookingDtoSend getBookingById(UUID id) {
        return bookingMapper.toDtoSend(bookingRepository.findById(id).orElseThrow());
    }

    @Override
    public String CONFIRMED(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        return message(booking);
    }

    @Override
    public String CANCELLED(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        return message(booking);
    }

    @Override
    public String COMPLETED(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.COMPLETE);
        return message(booking);
    }

    @Override
    public String FAILED(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.BookingStatus.FAILED);
        return message(booking);
    }
    public String message(Booking booking) {
        return bookingMapper.toDtoSend(bookingRepository.save(booking)).getStatus().name();
    }
}

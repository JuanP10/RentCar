package org.example.paymentservice.Service.ServiceImp;

import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpEntity;
import org.example.paymentservice.Models.Dtos.BookingRequest;
import org.example.paymentservice.Models.Dtos.PaymentDtoSave;
import org.example.paymentservice.Models.Dtos.PaymentDtoSend;
import org.example.paymentservice.Models.Entities.Payment;
import org.example.paymentservice.Models.Mappers.PaymentMapper;
import org.example.paymentservice.Repository.PaymentRepository;
import org.example.paymentservice.Service.PaymentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@Service
public class PaymentServiceImp implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final RestTemplate restTemplate;

    public PaymentServiceImp(PaymentRepository paymentRepository, PaymentMapper paymentMapper, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentDtoSend guardar(PaymentDtoSave paymentDtoSave) {
        String urlBase = "http://container3-app:8080/microservice/booking";
        String urlBooking = urlBase + "/" + paymentDtoSave.getIdBooking();
        String urlBookingCompletar = urlBase + "/completar/" + paymentDtoSave.getIdBooking();
        String urlBookingFail = urlBase + "/failed/" + paymentDtoSave.getIdBooking();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<BookingRequest> bookingResponseEntity =
                    restTemplate.getForEntity(urlBooking, BookingRequest.class);
            BookingRequest bookingRequest = Objects.requireNonNull(bookingResponseEntity.getBody());

            Payment payment = paymentMapper.toEntity(paymentDtoSave);

            if (bookingRequest.getStatus().equals(BookingRequest.Status.CONFIRMED.name()) ||
                    bookingRequest.getStatus().equals(BookingRequest.Status.FAILED.name())) {
                payment.setStatus(Payment.PaymentEstatus.COMPLETED);
                payment.setTransactionId(UUID.randomUUID());

                restTemplate.exchange(urlBookingCompletar, HttpMethod.PUT, requestEntity, String.class);
                return paymentMapper.toDtoSend(paymentRepository.save(payment));
            }
            payment.setStatus(Payment.PaymentEstatus.FAILED);
            PaymentDtoSend paymentDtoSend = paymentMapper.toDtoSend(paymentRepository.save(payment));

            if (!bookingRequest.getStatus().equals(BookingRequest.Status.COMPLETE.name())) {
                restTemplate.exchange(urlBookingFail, HttpMethod.PUT, requestEntity, String.class);
            }
            throw new RuntimeException("Fallo al procesar el pago");
        } catch (HttpClientErrorException ex) {

            throw new RuntimeException("Error HTTP al procesar el pago: " + ex.getStatusCode());
        } catch (RestClientException ex) {

            throw new RuntimeException("Error al conectarse al servidor: " + ex.getMessage());
        } catch (Exception ex) {

            throw new RuntimeException("Error inesperado al procesar el pago: " + ex.getMessage());
        }
    }


    @Override
    public Optional<PaymentDtoSend> findById(UUID id) {
        return Optional.ofNullable(paymentRepository.findById(id).map(paymentMapper::toDtoSend).orElseThrow(RuntimeException::new));
    }
}


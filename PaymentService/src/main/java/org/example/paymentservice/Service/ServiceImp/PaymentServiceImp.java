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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
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
        return null;
    }


    @Override
    public Optional<PaymentDtoSend> findById(UUID id) {
        return Optional.ofNullable(paymentRepository.findById(id).map(paymentMapper::toDtoSend).orElseThrow(RuntimeException::new));
    }
}


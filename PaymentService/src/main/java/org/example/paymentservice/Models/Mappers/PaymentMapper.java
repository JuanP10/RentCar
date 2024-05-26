package org.example.paymentservice.Models.Mappers;

import org.example.paymentservice.Models.Dtos.PaymentDtoSave;
import org.example.paymentservice.Models.Dtos.PaymentDtoSend;
import org.example.paymentservice.Models.Entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(PaymentDtoSave paymentDtoSave);
    PaymentDtoSend toDtoSend(Payment payment);
}

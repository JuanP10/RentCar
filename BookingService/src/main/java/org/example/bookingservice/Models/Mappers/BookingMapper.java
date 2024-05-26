package org.example.bookingservice.Models.Mappers;

import org.example.bookingservice.Models.Dtos.BookingDtoSave;
import org.example.bookingservice.Models.Dtos.BookingDtoSend;
import org.example.bookingservice.Models.Entities.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDtoSave toDtoSave(Booking booking);
    Booking toEntity(BookingDtoSave bookingDtoSave);
    BookingDtoSend toDtoSend(Booking booking);
}

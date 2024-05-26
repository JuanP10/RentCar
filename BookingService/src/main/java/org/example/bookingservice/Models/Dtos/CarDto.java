package org.example.bookingservice.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarDto {
    private UUID carId;
    private String model;
    private String maker;
    private Boolean avaliable;
}

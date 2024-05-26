package org.example.carinventoryservice.Models.Dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDtoSave {
    @NotBlank(message = "El Modelo Es Requerido")
    private String model;
    @NotBlank(message = "El Fabricante Es Requerida")
    private String maker;
}

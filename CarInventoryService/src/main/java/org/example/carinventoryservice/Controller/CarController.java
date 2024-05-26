package org.example.carinventoryservice.Controller;

import org.example.carinventoryservice.Models.Dtos.CarDtoSave;
import org.example.carinventoryservice.Models.Dtos.CarDtoSend;
import org.example.carinventoryservice.Service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("microservice/1.0.0./carrepository")
@AllArgsConstructor
@Validated

public class CarController {
    private final CarService carService;


}

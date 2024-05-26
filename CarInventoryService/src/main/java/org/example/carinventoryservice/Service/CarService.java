package org.example.carinventoryservice.Service;

import org.example.carinventoryservice.Models.Dtos.CarDtoSend;
import org.example.carinventoryservice.Models.Dtos.CarDtoSave;

import java.util.List;
import java.util.UUID;


public interface CarService {
    CarDtoSend saveCar(CarDtoSave carDtoSave);
    List<CarDtoSend> getAvaliableCars();

    CarDtoSend reserveCar(UUID idCar);
    CarDtoSend returnCar(UUID idCar, UUID idBooking);
}


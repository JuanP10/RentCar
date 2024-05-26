package org.example.carinventoryservice.Models.Mappers;

import org.example.carinventoryservice.Models.Dtos.CarDtoSave;
import org.example.carinventoryservice.Models.Dtos.CarDtoSend;
import org.example.carinventoryservice.Models.Entities.CarInventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDtoSave carToCarDtoSave(CarInventory carInventory);
    CarInventory carDtoSaveToCar(CarDtoSave carDtoSave);

    CarDtoSend carToCarDtoSend(CarInventory carInventory);
    CarInventory carDtoSendToCar(CarDtoSend carDtoSend);
}

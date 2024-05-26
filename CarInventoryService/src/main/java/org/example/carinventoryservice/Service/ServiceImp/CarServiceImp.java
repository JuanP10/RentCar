package org.example.carinventoryservice.Service.ServiceImp;
import org.example.carinventoryservice.Models.Dtos.CarDtoSend;
import org.example.carinventoryservice.Models.Dtos.CarDtoSave;
import org.example.carinventoryservice.Models.Entities.CarInventory;
import org.example.carinventoryservice.Models.Mappers.CarMapper;
import org.example.carinventoryservice.Repository.CarRepository;
import org.example.carinventoryservice.Service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final RestTemplate restTemplate;

    public CarServiceImp(CarRepository carRepository, CarMapper carMapper, RestTemplate restTemplate) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.restTemplate = restTemplate;
    }


    @Override
    public CarDtoSend saveCar(CarDtoSave carDtoSave) {
        Optional<CarInventory> car = carRepository.findByModel(carDtoSave.getModel());
        if (car.isPresent()) {
            throw new RuntimeException("Car already exists");
        }
        car = Optional.of(carMapper.carDtoSaveToCar(carDtoSave));
        car.get().setAvaliable(true);
        return carMapper.carToCarDtoSend(carRepository.save(car.get()));
    }



    @Override
    public List<CarDtoSend> getAvaliableCars() {
        return carRepository.findByAvaliableTrue().stream().map(carMapper::carToCarDtoSend).toList();
    }



    @Override
    public CarDtoSend reserveCar(UUID idCar) {
        CarInventory car = carRepository.findById(idCar)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if(car.getAvaliable()){
            car.setAvaliable(false);
            return carMapper.carToCarDtoSend(carRepository.save(car));
        }
        throw new RuntimeException("Car not avaliable");
    }

    @Override
    public CarDtoSend returnCar(UUID idCar, UUID idBooking) {
        String url = "http://container3-app:8080/microservice/booking/canceled/"+ idBooking;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        CarInventory car = carRepository.findById(idCar)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.getAvaliable()){
            car.setAvaliable(true);
            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            return carMapper.carToCarDtoSend(carRepository.save(car));
        }
        throw new RuntimeException("Car already avaliable");
    }
}

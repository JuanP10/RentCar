package org.example.carinventoryservice.Repository;

import org.example.carinventoryservice.Models.Entities.CarInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarInventory, UUID> {
    List<CarInventory> findByAvaliableTrue();

    Optional<CarInventory> findByModel(String model);

}

package com.airasia.Cars.Inventory.repository;

import com.airasia.Cars.Inventory.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends CrudRepository<Car, Integer> {

}

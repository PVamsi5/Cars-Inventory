package com.airasia.Cars.Inventory.controller;

import com.airasia.Cars.Inventory.service.CarInventoryService;
import com.airasia.Cars.Inventory.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CarInventoryController {

    Logger logger = LoggerFactory.getLogger(CarInventoryController.class);

    @GetMapping("/")
    public ModelAndView index () {
        logger.info("Get Hit with path '/'");
        return CarInventoryService.getHomePage();
    }

    @GetMapping("/cars")
    public List<Car> getByCompany(@RequestParam(value = "company",required = false) String company){
        logger.info("Get Hit with path '/cars'");
        return CarInventoryService.getAllCars(company);
    }

    @GetMapping("/cars/{id}")
    public Optional<Car> get(@PathVariable Integer id){
        logger.info("Get Hit with path '/cars/{id}'");

        return CarInventoryService.getCarById(id);
    }

    @DeleteMapping ("/cars/{id}")
    public void delete(@PathVariable Integer id){
        logger.info("Delete Hit with path '/cars/id'");
        CarInventoryService.deleteCar(id);
    }

    @PostMapping("/cars")
    public Car create(@RequestBody Car car) {
        logger.info("Post Hit with path '/cars'");
        CarInventoryService.createCar(car);
        return car;
    }

    @PutMapping("/cars/{id}")
    public Car update(@PathVariable Integer id, @RequestBody Car car){
        logger.info("Put Hit with path '/cars/id'");
        return CarInventoryService.updateCar(id, car);
    }

    @PatchMapping("/cars/{id}")
    public Car patch(@PathVariable Integer id, @RequestBody Car car){
        logger.info("Path Hit with path '/cars/id'");
        return CarInventoryService.updatePatchCar(id,car);
    }


}

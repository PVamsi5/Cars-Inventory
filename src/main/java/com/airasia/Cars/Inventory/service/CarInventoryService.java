package com.airasia.Cars.Inventory.service;

import com.airasia.Cars.Inventory.repository.CarsRepository;
import com.airasia.Cars.Inventory.controller.CarInventoryController;
import com.airasia.Cars.Inventory.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarInventoryService {

    static Logger logger = LoggerFactory.getLogger(CarInventoryController.class);

    //Initialising cars repository
    @Autowired
    private static CarsRepository carsRepository;

//    public CarInventoryService(CarsRepository carsRepository) {
//        CarInventoryService.carsRepository = carsRepository;
//    }

    public static ModelAndView getHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    public static List<Car> getAllCars(String company){
        if(company == null){
            return (List<Car>) carsRepository.findAll();
        }

        List<Car> result = new ArrayList<>();
        Iterable<Car> cars = carsRepository.findAll();
        cars.forEach((each) -> {if(each.getCompany().equals(company)){
            System.out.println(each.getCarId() + " " +company);
            result.add(each);
        }});

        return result;
    }

    public static Optional<Car> getCarById(Integer id){
        /*//        Car car = carsRepository.findById(id).orElse(null);
//        //handled the exception when the car is not available*/
        Optional<Car> car = Optional.empty();
        try{
            car = carsRepository.findById(id);
        }catch(Error e){
            logger.error(String.valueOf(e));
        }
        if(car.isEmpty()){
            logger.warn("Car with searching id does not exits.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return car;
    }

    public static void deleteCar(Integer id){
        //handled the exception when the car is not available
        if(!carsRepository.existsById(id)){
            logger.error("Car with given id not exist in database.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        carsRepository.deleteById(id);
    }

    public static Car createCar(Car car){
        if(Car.isValidCar(car)) carsRepository.save(car); //validating the car.
        else{
            logger.warn("Car Obj is not Valid");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return car;
    }

    public static Car updateCar(Integer id, Car car){
        Car newCar = carsRepository.findById(id).orElse(null);
        assert newCar != null;
        //check the pattern match and throw respective exception.
        if(Car.isValidCar(car)) newCar = car;
        else{
            logger.warn("Car Obj is not Valid");
        }
        newCar.setCarId(id);
        carsRepository.save(newCar);
        return newCar;
    }

    public static Car updatePatchCar(Integer id, Car car){
        Car newCar = carsRepository.findById(id).orElse(null);
        assert newCar != null;
        newCar.setCarId(id);
        if(car.getPrice()!=null){
            logger.warn("Patching the Price data");
            newCar.setPrice(car.getPrice());
        }
        if(car.getModel() != null){
            logger.warn("Patching the Model data");
            newCar.setModel(car.getModel());
        }
        if(car.getCompany() != null){
            logger.warn("Patching the Company data");
            newCar.setCompany(car.getCompany());
        }
        if(car.getReleaseYear()!=null){
            logger.warn("Patching the ReleaseYear data");
            newCar.setReleaseYear(car.getReleaseYear());
        }

        if(Car.isValidCar(newCar)) carsRepository.save(newCar);
        else{
            logger.warn("Patch Cannot be performed");
            logger.error("Car Obj data is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return newCar;
    }
}

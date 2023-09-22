package com.airasia.Cars.Inventory.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Table("CARS")
public class Car {
    @Id
    private Integer id;
    private Integer price;
    private String model;
    private String company;
    private Integer releaseYear;

    //raw data

    public Car() {
    }

    public Car(Integer carId,Integer price, String model, String company, int releaseYear) {
        this.id = carId;
        this.price = price;
        this.model = model;
        this.company = company;
        this.releaseYear = releaseYear;
    }

    public Integer getCarId() {
        return id;
    }
    public void setCarId(Integer carId) {
        this.id = carId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public static boolean isValidCar(Car car) {
        Logger logger = LoggerFactory.getLogger(Car.class);

        //check if price is integer
        boolean priceCheck = (car.getPrice() != null) && car.getPrice().toString().matches("[0-9]+");
        if(!priceCheck) logger.warn("Price is not Valid");

        //check if model is not null
        boolean modelCheck = car.getModel() != null && !Objects.equals(car.getModel(), "");
        if(!modelCheck) logger.warn("Model is not Valid");

        //check if company is not null
        boolean companyCheck = car.getCompany() != null && !Objects.equals(car.getCompany(), "");
        if(!companyCheck) logger.warn("Company is not Valid");

        //check if release year is valid.
        boolean releaseYearCheck = car.getReleaseYear() != null && car.getReleaseYear().toString().matches("[1-2][0-9]{3}");
        if(!releaseYearCheck) logger.warn("Release Year is not Valid");

        return modelCheck && priceCheck && companyCheck && releaseYearCheck;
    }
}

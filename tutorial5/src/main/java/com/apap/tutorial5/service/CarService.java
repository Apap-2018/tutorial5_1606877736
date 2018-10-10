package com.apap.tutorial5.service;

import com.apap.tutorial5.model.CarModel;

import java.util.Optional;

public interface CarService {
    Optional<CarModel> getCarById(Long id);
    void addCar(CarModel car);
    void deleteCar(CarModel car);
    void updateCar(Long id, CarModel car);
}

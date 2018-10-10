package com.apap.tutorial5.service;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.repository.CarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDb carDb;

    @Override
    public Optional<CarModel> getCarById(Long id){
        return carDb.findById(id);
    }

    @Override
    public void addCar (CarModel car){
        carDb.save(car);
    }

    @Override
    public void deleteCar (CarModel car) { carDb.delete(car);}

    @Override
    public void updateCar (Long id, CarModel car){
        CarModel newCar = getCarById(id).get();
        newCar.setAmount(car.getAmount());
        newCar.setBrand(car.getBrand());
        newCar.setPrice(car.getPrice());
        newCar.setType(car.getType());
    }
}

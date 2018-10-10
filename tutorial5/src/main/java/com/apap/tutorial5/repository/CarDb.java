package com.apap.tutorial5.repository;

import com.apap.tutorial5.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDb extends JpaRepository<CarModel, Long> {
    CarModel findByType(String type);
}

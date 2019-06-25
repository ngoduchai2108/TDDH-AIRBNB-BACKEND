package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageHouseRepository extends JpaRepository<Image,Long> {
//    List<Image> findAllByHouse(House house);
}

package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.House;

import java.util.List;

public interface HouseService {
    List<House> findAll();

    House findById(Long id);

    void save (House house);

    void remove(Long id);

    Boolean existsByName (String name);
}
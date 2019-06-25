package com.codegym.tddh.airbnb.service;


import com.codegym.tddh.airbnb.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesHouseService {
    Optional<Categories> findByName(String name);
    Boolean existsByName(String name);
    void save(Categories categories);
    List<Categories> findAll();

    Optional<Categories> findById(Long id);

    void remove(Long id);
}

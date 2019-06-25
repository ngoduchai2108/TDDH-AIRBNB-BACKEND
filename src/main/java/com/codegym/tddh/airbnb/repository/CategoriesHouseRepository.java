package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriesHouseRepository extends JpaRepository<Categories,Long> {
    Optional<Categories> findByName(String name);
    Boolean existsByName(String name);
    List<Categories> findAll();
//
//    Optional<Categories> findById(Long id);
//
//    void save(Categories categories);
//    void remove(Long id);
}

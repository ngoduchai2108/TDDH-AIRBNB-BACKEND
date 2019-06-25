package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Categories;
import com.codegym.tddh.airbnb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
//    List<House> findAllByCategoriesHouse(Categories categories);
}

package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Boolean existsByName (String name);
}

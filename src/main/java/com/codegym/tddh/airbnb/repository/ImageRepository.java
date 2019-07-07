package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteAllByHouse(House house);
    List<Image> findAllByHouse (House house);
    Image findFirstByHouse(House house);
    Boolean existsByHouse(House house);
}

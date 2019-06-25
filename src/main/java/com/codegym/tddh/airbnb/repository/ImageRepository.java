package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}

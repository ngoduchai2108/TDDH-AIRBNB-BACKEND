package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    List<Evaluation> findAllByHouseOrderById(House house);
    void deleteAllByHouse(House house);
}

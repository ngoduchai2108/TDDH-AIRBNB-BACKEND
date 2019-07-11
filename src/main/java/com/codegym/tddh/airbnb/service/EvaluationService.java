package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> findAllByHouse(House house);
    void save(Evaluation evaluation);
    Evaluation findById(Long id);
}

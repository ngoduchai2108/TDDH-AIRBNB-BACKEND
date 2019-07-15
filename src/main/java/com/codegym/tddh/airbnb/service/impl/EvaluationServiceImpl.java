package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.repository.EvaluationRepository;
import com.codegym.tddh.airbnb.service.EvaluationService;
import com.codegym.tddh.airbnb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    ReplyService replyService;
    @Override
    public List<Evaluation> findAllByHouse(House house) {
        return evaluationRepository.findAllByHouseOrderById(house);
    }

    @Override
    public void save(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id).get();
    }

    @Override
    public void deleteAllByHouse(House house) {
        List<Evaluation> evaluations = evaluationRepository.findAllByHouseOrderById(house);
        if (evaluations.isEmpty()) return;
        for (Evaluation evaluation: evaluations) {
            replyService.deleteAllByEvaluation(evaluation);
        }
        evaluationRepository.deleteAllByHouse(house);
    }

}

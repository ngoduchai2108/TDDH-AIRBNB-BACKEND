package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> findAllByEvaluationOrderById(Evaluation evaluation);
    void save(Reply reply);
}

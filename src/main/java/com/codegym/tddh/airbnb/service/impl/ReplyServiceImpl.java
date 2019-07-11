package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.Reply;
import com.codegym.tddh.airbnb.repository.ReplyRepository;
import com.codegym.tddh.airbnb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyRepository replyRepository;
    @Override
    public List<Reply> findAllByEvaluationOrderById(Evaluation evaluation) {
        return  replyRepository.findAllByEvaluationOrderById(evaluation);
    }

    @Override
    public void save(Reply reply) {
        replyRepository.save(reply);
    }
}

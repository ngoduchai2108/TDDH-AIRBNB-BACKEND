package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findAllByEvaluationOrderById(Evaluation evaluation);
}

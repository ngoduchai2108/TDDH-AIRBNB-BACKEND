package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findAllByEvaluationOrderById(Evaluation evaluation);
    void deleteAllByEvaluation(Evaluation evaluation);
}

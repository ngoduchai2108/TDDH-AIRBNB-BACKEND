package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Reply;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.service.EvaluationService;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ReplyService;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/replies")
public class ApiReplyController {
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    UserService userService;
    @Autowired
    ReplyService replyService;
    @PostMapping("/{id}")
    public ResponseEntity<?> createEvaluation(@Valid @RequestBody Reply reply,
                                              @PathVariable("id") Long id) {
        User user = userService.getUserByAuth();
        Evaluation evaluation = evaluationService.findById(id);
        reply.setEvaluation(evaluation);
        reply.setUser(user);
        replyService.save(reply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Reply>> listAllByHouseId(@PathVariable Long id){
        Evaluation evaluation = evaluationService.findById(id);
        List<Reply> replyList = replyService.findAllByEvaluationOrderById(evaluation);
        return new ResponseEntity<List<Reply>>( replyList,HttpStatus.OK);

    }

}

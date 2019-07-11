package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Booking;
import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.payload.response.ResponseMessage;
import com.codegym.tddh.airbnb.service.BookingService;
import com.codegym.tddh.airbnb.service.EvaluationService;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/evaluations")
public class ApiEvaluationController {
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    EvaluationService evaluationService;

    @PostMapping("/api/auth/evaluations/{id}")
    public ResponseEntity<?> createEvaluation(@Valid @RequestBody Evaluation evaluation,
                                        @PathVariable("id") Long id) {
        User user = userService.getUserByAuth();
        House house = houseService.findById(id);
        evaluation.setHouse(house);
        evaluation.setUser(user);
        evaluationService.save(evaluation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Evaluation>> listAllByHouseId(@PathVariable Long id){
        House house = houseService.findById(id);
        List<Evaluation> evaluationList= evaluationService.findAllByHouse(house);
        return new ResponseEntity<List<Evaluation>>( evaluationList,HttpStatus.OK);

    }

}

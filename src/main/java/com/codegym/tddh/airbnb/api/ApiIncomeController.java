package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Evaluation;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.IncomePerMonth;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.service.IncomeService;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/incomes")
public class ApiIncomeController {
    @Autowired
    IncomeService incomeService;
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<IncomePerMonth>> getAllIncomePerMonth(@RequestParam("year") String year ,@RequestParam("month") String month){
        User user =userService.getUserByAuth();
        List<IncomePerMonth> incomePerMonthList = incomeService.getAllIncomePerMonth(user,month,year);
        if (incomePerMonthList == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<IncomePerMonth>>(incomePerMonthList, HttpStatus.OK);

    }
}

package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.IncomePerMonth;
import com.codegym.tddh.airbnb.model.User;

import java.util.List;
public interface IncomeService {
    List<IncomePerMonth> getAllIncomePerMonth(User user, String month, String year);
}

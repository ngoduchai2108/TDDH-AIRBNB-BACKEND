package com.codegym.tddh.airbnb.model;

public class IncomePerMonth {
    private House house;
    private Double income;

    public IncomePerMonth(House house, Double income) {
        this.house = house;
        this.income = income;
    }

    public IncomePerMonth() {
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}

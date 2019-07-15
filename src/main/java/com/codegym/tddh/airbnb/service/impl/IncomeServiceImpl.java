package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Booking;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.IncomePerMonth;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.service.BookingService;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    BookingService bookingService;
    @Autowired
    HouseService houseService;

    @Override
    public List<IncomePerMonth> getAllIncomePerMonth(User user, String month, String year) {
        List<House> houseList = houseService.findAllByUser(user);
        if (houseList == null) return null;
        List<IncomePerMonth> incomePerMonthList = new ArrayList<>();
        for (House house : houseList) {
            IncomePerMonth income = new IncomePerMonth(house, getInComePerMonthByHouse(house, year, month));
            incomePerMonthList.add(income);
        }
        return incomePerMonthList;
    }

    private Double getIncomeByBooking(Booking booking) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = sdf.parse(booking.getStartDate());
            endDate = sdf.parse(booking.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert startDate != null;
        assert endDate != null;

        double diff = (endDate.getTime() - startDate.getTime()) / (24 * 3600 * 1000);
        double pr = booking.getHouse().getPrice();
        return booking.getHouse().getPrice() * diff;
    }

    private Double getInComePerMonthByHouse(House house, String year, String month) {
        List<Booking> bookings = bookingService.findAllByHouseAndMonth(house, month, year);
        if (bookings == null) return 0.0;
        Double sum = 0.0;
        for (Booking booking : bookings) {
            sum += getIncomeByBooking(booking);
        }
        return sum;
    }


}

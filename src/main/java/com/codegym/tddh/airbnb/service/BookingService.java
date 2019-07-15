package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Booking;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Booking findById(Long id);
    void save(Booking booking);
    void remove(Booking booking);
    List<Booking> findAllByUser(User user);
    Boolean userCanCancelBooking(String startDateStr);
    Boolean validateBooking(String startDateStr, String endDateStr );
    List<Booking> findAllByHouse(House house);
    List<Booking> findAllByHouseAndMonth(House house, String month ,  String year);

    List<Booking> findAllHistoryByUser(User user);
    Date parseStringToDate(String dateStr);
    void upDateCheckIn(Booking booking);
}

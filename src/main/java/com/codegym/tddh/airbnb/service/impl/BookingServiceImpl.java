package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Booking;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.repository.BookingRepository;
import com.codegym.tddh.airbnb.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking findByHouse(House house){
        return bookingRepository.findByHouse(house);
    }
    @Override
    public void save(Booking booking) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now =new Date();
        String oderDate= sdf.format(now);
        booking.setOderDate(oderDate);
        bookingRepository.save(booking);
    }

    @Override
    public void remove(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> findAllByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    @Override
    public Boolean userCanCancelBooking(String startDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date now = new Date();
        try {
            startDate = sdf.parse(startDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert startDate != null;
        long diff = (startDate.getTime() - now.getTime());
        return diff > (24 * 3600 * 1000);
    }

    @Override
    public Boolean validateBooking(String startDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = null, enDate = null;
        Date oderDate = new Date();
        try {
            startDate = sdf.parse(startDateStr);
            enDate = sdf.parse(endDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert enDate != null;
        long diffStartDateAndOderDate = (startDate.getTime() - oderDate.getTime());
        Boolean isStartDateOk = (diffStartDateAndOderDate > 0) && (diffStartDateAndOderDate / (24 * 3600 * 1000) < 10);
        Boolean isEndDateAfterOderDate = (enDate.getTime() - startDate.getTime()) > 0;
        return isStartDateOk && isEndDateAfterOderDate;
    }
}

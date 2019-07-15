package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Booking;
import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.payload.response.ResponseMessage;
import com.codegym.tddh.airbnb.security.userDetailsImpl.UserPrinciple;
import com.codegym.tddh.airbnb.service.BookingService;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/bookings")
public class ApiBookingController {
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;

    @PostMapping("/{id}")
    public ResponseEntity<?> bookAHouse(@Valid @RequestBody Booking booking,
                                        @PathVariable("id") Long id) {
        User user = userService.getUserByAuth();
        House house = houseService.findById(id);
        if ((house == null || house.getRented())) {
            return new ResponseEntity<>(new ResponseMessage("House is deleted or rented"),
                    HttpStatus.BAD_REQUEST);
        }
        if (bookingService.validateBooking(booking.getStartDate(), booking.getEndDate())) {
            booking.setUser(user);
            booking.setHouse(house);
            bookingService.save(booking);
            house.setRented(true);
            houseService.save(house);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> StartDate or EndDate Invalid "),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") Long id) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> listBookingByUser() {
        User user = userService.getUserByAuth();
        List<Booking> bookingList = bookingService.findAllByUser(user);
        if (bookingList.isEmpty()) {
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
    }
    @GetMapping("/history")
    public ResponseEntity<List<Booking>> listHistoryBookingByUser() {
        User user = userService.getUserByAuth();
        List<Booking> bookingList = bookingService.findAllHistoryByUser(user);
        if (bookingList.isEmpty()) {
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
    }

    @GetMapping("/by-houses/{id}")
    public ResponseEntity<List<Booking>> listBookingByHouseId(@PathVariable Long id){
        User user =userService.getUserByAuth();
        House house = houseService.findById(id);
        if (!house.getUser().getId().equals(user.getId())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Booking> bookingList = bookingService.findAllByHouse(house);
        if (bookingList.isEmpty()) {
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        User user = userService.getUserByAuth();
        if (!(user.getId().equals(booking.getUser().getId())))return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (bookingService.userCanCancelBooking(booking.getStartDate())) {
            bookingService.remove(booking);
            House house = booking.getHouse();
            house.setRented(false);
            houseService.save(house);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Cannot Cancel Booking"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @DeleteMapping("owners/{id}")
    public ResponseEntity<?>houseOwnerRemoveBooking(@PathVariable Long id){
        Booking booking = bookingService.findById(id);
        House house = booking.getHouse();
        User user = userService.getUserByAuth();
        if(house.getUser().getId().equals(user.getId())){
            bookingService.remove(booking);
            house.setRented(false);
            houseService.save(house);
        }else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> upDateCheckInBooking(@PathVariable("id") Long id) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookingService.upDateCheckIn(booking);
        return new ResponseEntity<>( HttpStatus.OK);
    }

//    private User getUserByAuth() {
//        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long user_id = ((UserPrinciple) userPrinciple).getId();
//        return userService.findById(user_id);
//    }
}

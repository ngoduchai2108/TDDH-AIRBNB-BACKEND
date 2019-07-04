package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.payload.form.SearchHouseForm;
import com.codegym.tddh.airbnb.security.userDetailsImpl.UserPrinciple;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApiHouseController {

    @Autowired
    UserService userService;

    @Autowired
    HouseService houseService;

//----------------------Get All House----------------------------
    @GetMapping("/houses")
    public ResponseEntity<List<House>> listAllHouse() {
        List<House> houses = houseService.findAll();
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }
// ----------------------Get all house not Rented ----------------
    @GetMapping("/houses/status/false")
    public ResponseEntity<List<House>> listAllHouseByRented() {
        List<House> houses = houseService.findAllByNotRented();
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }

    @PostMapping("/houses/search")
    public ResponseEntity<List<House>> listAllHouseBySearchValue(@RequestBody SearchHouseForm searchHouseForm) {
        List<House> houses = houseService.findAllBySearchValue(searchHouseForm);
        if(houses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }

    @GetMapping("/house")
    public ResponseEntity<List<House>> listHouseByUser() {
        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserPrinciple) userPrinciple).getId();
        User user = userService.findById(id);
        List<House> houses = houseService.findAllByUser(user);
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<House> getHouseId(@PathVariable("id") Long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<House>(house, HttpStatus.OK);
    }


    @PostMapping("/house")
    public ResponseEntity<Long> createHouse(@Valid @RequestBody House house,
                                            UriComponentsBuilder uriComponentsBuilder,
                                            HttpServletRequest request) {
        String jwt = request.getHeader("TDDH");
        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserPrinciple) userPrinciple).getId();
        User user = userService.findById(id);
        house.setUser(user);
        house.setRented(false);
        houseService.save(house);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/house/{id}").buildAndExpand(house.getId()).toUri());
        return new ResponseEntity<Long>(house.getId(), headers, HttpStatus.CREATED);
    }

    @PutMapping("/house/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable("id") long id,
                                             @RequestBody House house) {
        House currentHouse = houseService.findById(id);
        if (currentHouse == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }

        currentHouse.setName(house.getName());
        currentHouse.setAddress(house.getAddress());
        currentHouse.setQuantityBedroom(house.getQuantityBedroom());
        currentHouse.setQuantityBathroom(house.getQuantityBathroom());
        currentHouse.setDescription(house.getDescription());
        currentHouse.setPrice(house.getPrice());
        currentHouse.setCategories(house.getCategories());

        houseService.save(currentHouse);
        return new ResponseEntity<House>(currentHouse, HttpStatus.OK);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable("id") long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        houseService.remove(id);
        return new ResponseEntity<House>(HttpStatus.NO_CONTENT);
    }
}

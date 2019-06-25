package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class ApiHouseController {

    @Autowired
    HouseService houseService;

    @GetMapping("/house")
    public ResponseEntity<List<House>> listAllHouse(){
        List<House> houses = houseService.findAll();
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses,HttpStatus.OK);
    }

    @PostMapping("/house")
    public ResponseEntity<Void> createHouse(@Valid @RequestBody House house,
                                         UriComponentsBuilder uriComponentsBuilder) {
//        if (houseService.existsByName(house.getName())) {
//            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//        }
        houseService.save(house);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/house/{id}").buildAndExpand(house.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/house/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable("id") long id,
                                             @RequestBody House house){
        House currentHouse = houseService.findById(id);
        if (currentHouse == null) {
            return  new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }

        currentHouse.setName(house.getName());
        currentHouse.setAddress(house.getAddress());
        currentHouse.setQuantityBedroom(house.getQuantityBedroom());
        currentHouse.setQuantityBathroom(house.getQuantityBathroom());
        currentHouse.setDescription(house.getDescription());
        currentHouse.setPrice(house.getPrice());
        currentHouse.setCategories(house.getCategories());

        houseService.save(currentHouse);
        return  new ResponseEntity<House>(currentHouse, HttpStatus.OK);
    }

    @DeleteMapping("/house/{id}")
    public  ResponseEntity<House> deleteHouse(@PathVariable("id") long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return  new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        houseService.remove(id);
        return new ResponseEntity<House>(HttpStatus.NO_CONTENT);
    }

}

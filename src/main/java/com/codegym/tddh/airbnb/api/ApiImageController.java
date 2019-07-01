package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApiImageController {

    private static final Logger logger = LoggerFactory.getLogger(ApiImageController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private HouseService houseService;

    @PostMapping(value = "/upload-file/{id}")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        if (file == null) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        try {
            Image image = new Image();
            House house = houseService.findById(id);
            image.setHouse(house);
            image.setName(file.getOriginalFilename());
            imageService.save(image);
            imageService.storeFile(file);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("aaaaaaaaaaa");
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

    @GetMapping(value = "/download-file/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getFile(@PathVariable("id") Long id) {

        Resource file = imageService.getFileById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @DeleteMapping(value = "/delete-all-file/{id}")
    public ResponseEntity<Void> deleteAllByHouse (@PathVariable("id") Long houseid){
       House house =houseService.findById(houseid);
       if (house ==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       imageService.deleteAllByHouse(house);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/download-all-file/{id}")
    public ResponseEntity<List<Long>> listAllImage(@PathVariable("id") Long id) {
        House house = houseService.findById(id);
        List<Image> images = imageService.findAllByHouse(house);
        ArrayList listImgId = new ArrayList();
        for(Image img:images){
            listImgId.add(img.getId());
        }
        return new ResponseEntity<List<Long>>(listImgId,HttpStatus.OK);
    }
    @DeleteMapping("delete-file/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable("id") Long id) {
        Image image = imageService.findById(id);
        if (image == null) {
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        imageService.deleteById(id);
        return new ResponseEntity<Image>(HttpStatus.OK);
    }
}

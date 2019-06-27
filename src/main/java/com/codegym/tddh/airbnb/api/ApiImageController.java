package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.payload.response.UploadFileResponse;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApiImageController {

    private static final Logger logger = LoggerFactory.getLogger(ApiImageController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private HouseService houseService;

    @PostMapping("/upload-file/{houseid}")
    public UploadFileResponse uploadFileResponse(@RequestParam("file") MultipartFile file, @PathVariable("houseid") Long id) {
        Image image = imageService.storeFile(file, id);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(String.valueOf(image.getId()))
                .toUriString();

        return new UploadFileResponse(image.getName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/download-file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
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
        imageService.remove(id);
        return new ResponseEntity<Image>(HttpStatus.OK);
    }

    @DeleteMapping("delete-all-file/{id}")
    public ResponseEntity<Void> deleteAllImage(@PathVariable("id") Long id) {
        House house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        imageService.deleteAllByHouse(house);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

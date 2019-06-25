package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.payload.exception.FileStorageException;
import com.codegym.tddh.airbnb.payload.response.UploadFileResponse;
import com.codegym.tddh.airbnb.security.userDetailsImpl.UserPrinciple;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import com.codegym.tddh.airbnb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApiHouseController {
    public static long HOUSE_ID = 0;

    private static final Logger logger = LoggerFactory.getLogger(ApiHouseController.class);

    @Autowired
    UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    HouseService houseService;

    @GetMapping("/houses")
    public ResponseEntity<List<House>> listAllHouse() {
        List<House> houses = houseService.findAll();
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }

    @GetMapping("/house")
    public ResponseEntity<List<House>> listHouseById() {
        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserPrinciple) userPrinciple).getId();
        User user = userService.findById(id);
        List<House> houses = houseService.findAllByUser(user);
        if (houses.isEmpty()) {
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }

    @PostMapping("/house")
    public ResponseEntity<Void> createHouse(@Valid @RequestBody House house,
                                            UriComponentsBuilder uriComponentsBuilder,
                                            HttpServletRequest request) {
        String jwt = request.getHeader("TDDH");
        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserPrinciple) userPrinciple).getId();
        User user = userService.findById(id);
        house.setUser(user);

        houseService.save(house);
        HOUSE_ID = house.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/house/{id}").buildAndExpand(house.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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


    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFileResponse(@RequestParam("file") MultipartFile file) {
        Image image = storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(image.getId()))
                .toUriString();

        return new UploadFileResponse(image.getName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    public Image storeFile(MultipartFile file) {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (imageName.contains("..")) {
                throw new FileStorageException("Sorry! Image name contains invalid path sequence " + imageName);
            }
            House house = houseService.findById(HOUSE_ID);
            Image image = new Image(imageName, file.getContentType(), file.getBytes());
            image.setHouse(house);
            return imageService.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }
}

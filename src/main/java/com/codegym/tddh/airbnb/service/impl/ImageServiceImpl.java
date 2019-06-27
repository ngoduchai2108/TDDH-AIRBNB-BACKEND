package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.payload.exception.FileStorageException;
import com.codegym.tddh.airbnb.payload.exception.MyFileNotFoundException;
import com.codegym.tddh.airbnb.repository.ImageRepository;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    HouseService houseService;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image getFile(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }

    @Override
    public Image storeFile(MultipartFile file, Long id) {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (imageName.contains("..")) {
                throw new FileStorageException("Sorry! Image name contains invalid path sequence " + imageName);
            }
            House house = houseService.findById(id);
            Image image = new Image(imageName, file.getContentType(), file.getBytes());
            image.setHouse(house);
            return imageRepository.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }

    @Override
    public void deleteAllByHouse(House house) {
        imageRepository.deleteAllByHouse(house);
    }

    @Override
    public Image findById(Long id) {
      return  imageRepository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> findAllByHouse(House house) {
        return imageRepository.findAllByHouse(house);
    }


}

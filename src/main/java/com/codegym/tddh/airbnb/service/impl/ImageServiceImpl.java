package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.payload.exception.FileStorageException;
import com.codegym.tddh.airbnb.payload.exception.MyFileNotFoundException;
import com.codegym.tddh.airbnb.repository.ImageRepository;
import com.codegym.tddh.airbnb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
      return  imageRepository.save(image);
    }

    @Override
    public Image getFile(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }
}

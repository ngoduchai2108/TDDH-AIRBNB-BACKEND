package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    void storeFile(MultipartFile file, String fileName);

    void save (Image image);

    Resource loadFile (String name);

    Resource getFileById(Long id);

    void deleteAllByHouse(House house);

    void deleteById(Long id);

    Image findById(Long id);

    List<Image> findAllByHouse(House house);

    Resource findFirstByHouse(House house);

    Boolean existsByHouse(House house);
}

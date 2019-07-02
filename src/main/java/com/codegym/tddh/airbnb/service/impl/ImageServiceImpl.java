package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.repository.ImageRepository;
import com.codegym.tddh.airbnb.service.HouseService;
import com.codegym.tddh.airbnb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    HouseService houseService;

    @Autowired
    ImageRepository imageRepository;

    private  final Path root = Paths.get("/home/dinh/Desktop/TDDH/backend-v1/TDDH-AIRBNB-BACKEND/src/main/resources/upload-dir/");




    @Override
    public void storeFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }catch (IOException ex) {
            throw  new  RuntimeException("FAIL!");
        }
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Resource loadFile(String name) {
        try {
            Path file = root.resolve(name);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return  resource;
            }else  {
                throw  new RuntimeException("FAIL!");
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public Resource getFileById(Long id) {
        Image image =imageRepository.getOne(id);
        return loadFile(image.getName());
    }

    @Override
    public void deleteAllByHouse(House house) {
        imageRepository.deleteAllByHouse(house);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public List<Image> findAllByHouse(House house) {
        return imageRepository.findAllByHouse(house);
    }
}

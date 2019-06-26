package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image getFile(Long id);

    Image storeFile(MultipartFile file, Long id);
}

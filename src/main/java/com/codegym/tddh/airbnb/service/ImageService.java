package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Image;

public interface ImageService {

    Image save(Image image);

    Image getFile(Long id);
}

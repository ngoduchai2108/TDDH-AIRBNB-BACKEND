package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Categories;
import com.codegym.tddh.airbnb.repository.CategoriesHouseRepository;
import com.codegym.tddh.airbnb.service.CategoriesHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesHouseServiceImpl implements CategoriesHouseService {
    @Autowired
    private CategoriesHouseRepository categoriesHouseRepository;


    @Override
    public Optional<Categories> findByName(String name) {
        return categoriesHouseRepository.findByName(name);
    }

    @Override
    public Boolean existsByName(String name) {
        return categoriesHouseRepository.existsByName(name);
    }

    @Override
    public void save(Categories categories) {
        categoriesHouseRepository.save(categories);
    }

    @Override
    public List<Categories> findAll() {
        return categoriesHouseRepository.findAll();
    }

    @Override
    public Optional<Categories> findById(Long id) {
        return categoriesHouseRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        categoriesHouseRepository.deleteById(id);
    }
}

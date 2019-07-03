package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.repository.HouseRepository;
import com.codegym.tddh.airbnb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseRepository houseRepository;

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public List<House> findAllByUser(User user) {
        return houseRepository.findAllByUser(user);
    }

    @Override
    public House findById(Long id) {
        return houseRepository.findById(id).get();
    }

    @Override
    public void save(House house) {
        houseRepository.save(house);
    }

    @Override
    public void remove(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return houseRepository.existsByName(name);
    }

    @Override
    public List<House> findAllBySearchValue() {
        return houseRepository.findAllBySearchValue("s",1,null,1000.0,4.0);
    }
}

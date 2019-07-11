package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    User findById(Long id);
    Boolean existsByEmail(String email);
    void save(User user);
    User getUserByAuth();
}

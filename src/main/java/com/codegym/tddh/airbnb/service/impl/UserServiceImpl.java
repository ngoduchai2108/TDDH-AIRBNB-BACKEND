package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.User;
import com.codegym.tddh.airbnb.repository.UserRepository;
import com.codegym.tddh.airbnb.security.userDetailsImpl.UserPrinciple;
import com.codegym.tddh.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByAuth() {
        Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long user_id = ((UserPrinciple) userPrinciple).getId();
        return findById(user_id);
    }

}

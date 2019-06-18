package com.codegym.tddh.airbnb.service.impl;

import com.codegym.tddh.airbnb.model.Role;
import com.codegym.tddh.airbnb.model.RoleName;
import com.codegym.tddh.airbnb.repository.RoleRepository;
import com.codegym.tddh.airbnb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}

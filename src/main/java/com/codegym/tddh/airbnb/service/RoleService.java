package com.codegym.tddh.airbnb.service;

import com.codegym.tddh.airbnb.model.Role;
import com.codegym.tddh.airbnb.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}

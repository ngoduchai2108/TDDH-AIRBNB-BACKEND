package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.Role;
import com.codegym.tddh.airbnb.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByEmail(String email);
    Boolean existsByEmail(String email);
}

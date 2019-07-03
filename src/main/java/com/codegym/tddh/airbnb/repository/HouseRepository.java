package com.codegym.tddh.airbnb.repository;

import com.codegym.tddh.airbnb.model.House;
import com.codegym.tddh.airbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Boolean existsByName (String name);
    List<House> findAllByUser (User user);
    List<House> findAllByIsRented(boolean isRented);
    @Query("SELECT h FROM House h WHERE (:address is null or h.address LIKE CONCAT('%',:address,'%') ) and (:quantityBathroom is null"
            + " or h.quantityBathroom = :quantityBathroom) and (:quantityBedroom is null"
            +" or h.quantityBedroom = :quantityBedroom) and (:maxPrice is null"
            + " or h.price < :maxPrice) and (:minPrice is null or h.price > :minPrice ) and (h.isRented = false )")
    List<House> findAllBySearchValue(@Param("address") String address,@Param("quantityBathroom")Integer quantityBathroom,
                                     @Param("quantityBedroom") Integer quantityBedroom, @Param("maxPrice") Double maxPrice,
                                     @Param("minPrice")Double minPrice);



}

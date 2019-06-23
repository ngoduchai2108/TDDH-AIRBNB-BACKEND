package com.codegym.tddh.airbnb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String houseName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank
    private Integer quantityBedroom;

    @NotBlank
    private Integer quantityBathroom;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    @NotBlank
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoriesHouse_id")
    private CategoriesHouse categoriesHouse;

    @OneToMany(targetEntity = ImageHouse.class)
    private List<ImageHouse> imageHouses;

    public House() {
    }

    public House(String houseName, String address,
                 Integer quantityBedroom, Integer quantityBathroom,
                 String description,
                 Double price, CategoriesHouse categoriesHouse) {
        this.houseName = houseName;
        this.address = address;
        this.quantityBedroom = quantityBedroom;
        this.quantityBathroom = quantityBathroom;
        this.description = description;
        this.price = price;
        this.categoriesHouse = categoriesHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getQuantityBedroom() {
        return quantityBedroom;
    }

    public void setQuantityBedroom(Integer quantityBedroom) {
        this.quantityBedroom = quantityBedroom;
    }

    public Integer getQuantityBathroom() {
        return quantityBathroom;
    }

    public void setQuantityBathroom(Integer quantityBathroom) {
        this.quantityBathroom = quantityBathroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoriesHouse getCategoriesHouse() {
        return categoriesHouse;
    }

    public void setCategoriesHouse(CategoriesHouse categoriesHouse) {
        this.categoriesHouse = categoriesHouse;
    }
}

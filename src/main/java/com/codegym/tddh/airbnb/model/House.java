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
    private Integer quantityBadroom;

    @NotBlank
    private Integer quantityBathroom;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    @NotBlank
    private String price;

    @ManyToOne
    @JoinColumn(name = "categuriesHouse_id")
    private CateguriesHouse categuriesHouse;

    @OneToMany(targetEntity = ImageHouse.class)
    private List<ImageHouse> imageHouses;

    public House() {
    }

    public House(@NotBlank @Size(min = 3, max = 50) String houseName, @NotBlank @Size(min = 3, max = 50) String address,
                 @NotBlank Integer quantityBadroom, @NotBlank Integer quantityBathroom,
                 @NotBlank @Size(min = 10, max = 100) String description,
                 @NotBlank String price, CateguriesHouse categuriesHouse) {
        this.houseName = houseName;
        this.address = address;
        this.quantityBadroom = quantityBadroom;
        this.quantityBathroom = quantityBathroom;
        this.description = description;
        this.price = price;
        this.categuriesHouse = categuriesHouse;
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

    public Integer getQuantityBadroom() {
        return quantityBadroom;
    }

    public void setQuantityBadroom(Integer quantityBadroom) {
        this.quantityBadroom = quantityBadroom;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CateguriesHouse getCateguriesHouse() {
        return categuriesHouse;
    }

    public void setCateguriesHouse(CateguriesHouse categuriesHouse) {
        this.categuriesHouse = categuriesHouse;
    }
}

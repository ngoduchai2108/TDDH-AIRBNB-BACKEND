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
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String address;

    private Integer quantityBedroom;

    private Integer quantityBathroom;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoriesHouse_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = Image.class)
    private List<Image> images;

    public House() {
    }

    public House( String name, String address, Integer quantityBedroom, Integer quantityBathroom,  String description, Double price) {
        this.name = name;
        this.address = address;
        this.quantityBedroom = quantityBedroom;
        this.quantityBathroom = quantityBathroom;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

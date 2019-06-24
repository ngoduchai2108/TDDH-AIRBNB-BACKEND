package com.codegym.tddh.airbnb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categoriesHouse")
public class CategoriesHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 50)
    private String categoriesName;

    @OneToMany(targetEntity = House.class)
    private List<House> houses;

    public CategoriesHouse(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
}

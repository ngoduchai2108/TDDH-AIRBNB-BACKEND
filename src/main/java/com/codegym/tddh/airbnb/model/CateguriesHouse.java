package com.codegym.tddh.airbnb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categuries")
public class CateguriesHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 50)
    private String categuriesName;

    @OneToMany(targetEntity = House.class)
    private List<House> houses;

    public CateguriesHouse(@NotBlank @Size(min = 6, max = 50) String categuriesName) {
        this.categuriesName = categuriesName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateguriesName() {
        return categuriesName;
    }

    public void setCateguriesName(String categuriesName) {
        this.categuriesName = categuriesName;
    }
}

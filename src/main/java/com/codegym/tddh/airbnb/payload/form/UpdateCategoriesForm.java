package com.codegym.tddh.airbnb.payload.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateCategoriesForm {

    @NotBlank
    @Size(min = 3, max = 60)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

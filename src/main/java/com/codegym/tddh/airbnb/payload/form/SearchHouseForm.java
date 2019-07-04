package com.codegym.tddh.airbnb.payload.form;

public class SearchHouseForm {
    private String address;
    private Integer quantityBathroom;
    private Integer quantityBedroom;
    private Double maxPrice;
    private Double minPrice;

    public SearchHouseForm() {
    }

    public SearchHouseForm(String address, Integer quantityBathroom, Integer quantityBedroom, Double maxPrice, Double minPrice) {
        this.address = address;
        this.quantityBathroom = quantityBathroom;
        this.quantityBedroom = quantityBedroom;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getQuantityBathroom() {
        return quantityBathroom;
    }

    public void setQuantityBathroom(Integer quantityBathroom) {
        this.quantityBathroom = quantityBathroom;
    }

    public Integer getQuantityBedroom() {
        return quantityBedroom;
    }

    public void setQuantityBedroom(Integer quantityBedroom) {
        this.quantityBedroom = quantityBedroom;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
}

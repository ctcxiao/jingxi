package com.example.employee.entity;

public class UpdateProEntity {
    private String name;
    private String description;
    private double price;

    public UpdateProEntity(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UpdateProEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

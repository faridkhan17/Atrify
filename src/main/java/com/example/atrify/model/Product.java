package com.example.atrify.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private final int id;
    private String name;
    private int stock;

    // Constructor
    public Product(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("stock") int stock) {

        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

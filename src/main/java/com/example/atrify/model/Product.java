package com.example.atrify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.util.UUID;

public class Product {
    private final int id;
    private String name;
    private int stock;

    // Constructor
    public Product(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("stock") int stock) {
        Assert.notNull(stock, "you must provide a stock of the product");
        Assert.notNull(name, "product name cannot be empty");
        Assert.hasLength(name, "name is empty");
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public Product(int i, String xxx, int stock, int id) {

        this.id = id;
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

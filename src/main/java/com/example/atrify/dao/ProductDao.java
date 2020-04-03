package com.example.atrify.dao;

import com.example.atrify.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    // product with id
    boolean insertProduct(Product product);

    default List<Product> getAllProducts() {
        return getAllProducts();
    }

    default boolean updateProduct(Product product) {
        return updateProduct(product);
    }

    default Optional<Product> getProduct(int id) {
        return getProduct(id);
    }

    default boolean buyProduct(int id, int stock) {
        return buyProduct(id, stock);
    }

}

package com.example.atrify.dao;

import com.example.atrify.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao {

    // product with id
    boolean insertProduct(Product product);

    // product without id, randomly generated
    /*
    default boolean insertProduct(Product product) {

        return insertProduct(product);
    }*/


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

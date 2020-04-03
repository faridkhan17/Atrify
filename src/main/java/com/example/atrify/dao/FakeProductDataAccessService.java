package com.example.atrify.dao;

import com.example.atrify.model.Product;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeProductDataAccessService implements ProductDao {

    private static List<Product> DB = new ArrayList<>();

    @Override
    public boolean insertProduct(Product product) {
        return DB.add(new Product(product.getId(), product.getName(), product.getStock()));
    }

    @Override
    public List<Product> getAllProducts() {
        return DB;
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return DB
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    @Override
    public boolean updateProduct(Product product) {
        return getProduct(product.getId())
                .map(person -> {
                    int indexOfProductToDelete = DB.indexOf(person);
                    if (indexOfProductToDelete >= 0) {
                        DB.set(indexOfProductToDelete, new Product(product.getId(), product.getName(), product.getStock()));
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    @Override
    public boolean buyProduct(int id, int stock) {
        Optional<Product> product = getProduct(id);
        if (product.isPresent()) {
            Product myProduct = product.get();
            if (myProduct.getStock() - stock < 0) {
                return false;
            }
            myProduct.setStock(myProduct.getStock() - stock);
            return updateProduct(myProduct);
        }
        return true;
    }
}

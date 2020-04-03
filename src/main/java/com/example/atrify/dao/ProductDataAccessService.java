package com.example.atrify.dao;

import com.example.atrify.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeDao")
public class ProductDataAccessService implements ProductDao {

    private static List<Product> DB = new ArrayList<>();


    // 4. It must be possible to refill the product stock
    @Override
    public boolean insertProduct(Product product) {
        return DB.add(new Product(product.getId(), product.getName(), product.getStock()));

    }


    // 2. It must be possible to request the entire product and product stock information
    @Override
    public List<Product> getAllProducts() {
        return DB;
    }


    // 3. It must be possible to request the stock of an individual product
    @Override
    public Optional<Product> getProduct(int id) {
        return DB
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }


    // update product in DB, also used for buyProduct function
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


    // 5. Buying the product decreases the stock
    // 6. It is not allowed to buy more product items than its stock provides
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

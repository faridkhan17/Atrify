package com.example.atrify.service;

import com.example.atrify.dao.ProductDao;
import com.example.atrify.model.Product;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("fakeDao") ProductDao productDao) {
        this.productDao = productDao;
    }

    public boolean addProduct(Product product) {
        return productDao.insertProduct(product);
    }

    public List<Product> getAllProducts() {

        return productDao.getAllProducts();
    }

    public boolean updateProduct(Product product) {

        return productDao.updateProduct(product);
    }

    public Optional<Product> getProduct(int id) {

        return productDao.getProduct(id);
    }

    public boolean buyProduct(int id, int stock) {
        return productDao.buyProduct(id, stock);
    }
}

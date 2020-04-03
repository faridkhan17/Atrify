package com.example.atrify;

import com.example.atrify.dao.ProductDao;
import com.example.atrify.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class AtrifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtrifyApplication.class, args);
    }

    @Bean

    CommandLineRunner runner(ProductDao productDao) {
        return args -> {
            productDao.insertProduct(new Product(1, "xxx", 100));
        };
    }

}
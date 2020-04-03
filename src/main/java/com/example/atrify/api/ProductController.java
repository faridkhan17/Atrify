package com.example.atrify.api;

import com.example.atrify.model.Product;
import com.example.atrify.service.ProductService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping("/add")
    public boolean addProduct(@RequestBody Product product) {

        return productService.addProduct(product);
    }

    @GetMapping("/getProduct/{id}")
    public Optional<Product> getProduct(@NotNull @PathVariable("id") int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/list")
    public List<Product> getProductList() {

        return productService.getAllProducts();
    }

    @PutMapping("/update")
    public boolean updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product); }

    @PutMapping("/buy/{id}/{quantity}")
    public boolean buyProduct(@NotNull @PathVariable("id") int id, @NotNull @PathVariable("quantity") int stock) {
        return productService.buyProduct(id, stock);
    }

}

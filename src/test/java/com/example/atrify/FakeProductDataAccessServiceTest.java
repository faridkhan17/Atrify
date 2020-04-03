package com.example.atrify;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.atrify.dao.FakeProductDataAccessService;
import com.example.atrify.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeProductDataAccessServiceTest {

    private FakeProductDataAccessService underTest;

    @Before
    public void setUp() {
        underTest = new FakeProductDataAccessService();
    }

    @Test
    public void canPerformCrud() {

        // Given product called xxx stock 100
        int idOne = 1;
        Product productOne = new Product(idOne, "xxx", 100);


        // ...And yyy stock 80
        int idTwo = 2;
        Product productTwo = new Product(idTwo, "yyy", 80);


        // When xxx and yyy added to db
        underTest.insertProduct(productOne);
        underTest.insertProduct(productTwo);


        // Then can retrieve xxx by id
        assertThat(underTest.getProduct(idOne))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(productOne));


        // ...And also yyy by id
        assertThat(underTest.getProduct(idTwo))
                .isPresent()
                .hasValueSatisfying(personFromDb -> assertThat(personFromDb).isEqualToComparingFieldByField(productTwo));


        // When get all products
        List<Product> products = underTest.getAllProducts();


        // ...List should have size 2 and should have both xxx and yyy
        assertThat(products)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(productOne, productTwo);


        // ... An update request (xxx name to zzz)
        Product productUpdate = new Product(idOne, "zzz", 100);


        // When Update
        assertThat(underTest.updateProduct(productUpdate)).isEqualTo(1);


        // Then when get product with idOne then should have name as xxx -> zzz
        assertThat(underTest.getProduct(idOne))
                .isPresent()
                .hasValueSatisfying(product -> assertThat(product).isEqualToComparingFieldByField(productUpdate));


        // Buying the product decreases the stock
        // It is not allowed to buy more product items than its stock provides
        Product productBuy = new Product(idOne, "xxx", 60);

        // When bought
        assertThat(underTest.buyProduct(idOne, 40)).isEqualTo(1);


        // Then when get product with idOne then should have stock as 100 -> 60
        assertThat(underTest.getProduct(idOne)).isPresent()
                .hasValueSatisfying(product -> assertThat(product).isEqualToComparingFieldByField(productBuy));

    }

}
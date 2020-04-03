package com.example.atrify;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.atrify.dao.ProductDataAccessService;
import com.example.atrify.model.Product;
import org.junit.Before;
import org.junit.Test;


public class ProductDataAccessServiceTest {

    private ProductDataAccessService underTest;
    // Given product called xxx stock 100
    int idOne = 1;
    Product productOne = new Product(idOne, "xxx", 100);


    // yyy stock 80
    int idTwo = 2;
    Product productTwo = new Product(idTwo, "yyy", 80);

    // ... An update request (xxx name to zzz)
    Product productUpdate = new Product(idOne, "zzz", 100);

    // buy product
    Product productBuy = new Product(idOne, "zzz", 60);


    @Before
    public void setUp() {
        underTest = new ProductDataAccessService();
    }

    @Test
    public void insertTest() {


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

    }
        @Test
        public void gellProductsTest() {
        // When get all products
        List<Product> products = underTest.getAllProducts();


        // ...List should have size 2 and should have both xxx and yyy
        assertThat(products)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(productOne, productTwo);

        }
    @Test
    public void productUpdateTest() {

        // When Update
        assertThat(underTest.updateProduct(productUpdate)).isEqualTo(true);

    }

    @Test
    public void productUpdateTest2() {
        // Then when get product with idOne then should have name as xxx -> zzz
        assertThat(underTest.getProduct(idOne))
                .isPresent()
                .hasValueSatisfying(product -> assertThat(product).isEqualToComparingFieldByField(productUpdate));

    }
    @Test
    public void ProductBuyTest() {
        // It is not allowed to buy more product items than its stock provides

        // When bought
        assertThat(underTest.buyProduct(idOne, 40)).isEqualTo(true);

    }
    @Test
    public void ProductBuyTest2() {
        // Then when get product with idOne then should have stock as 100 -> 60
        // Buying the product decreases the stock

        assertThat(underTest.getProduct(idOne)).isPresent()
                .hasValueSatisfying(product -> assertThat(product).isEqualToComparingFieldByField(productBuy));

    }

}
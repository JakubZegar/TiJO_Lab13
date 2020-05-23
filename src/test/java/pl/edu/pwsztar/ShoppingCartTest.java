package pl.edu.pwsztar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Podczas implementacji prosze pamietac o zasadzie F.I.R.S.T
public class ShoppingCartTest {

    private ShoppingCartOperation shoppingCart = new ShoppingCart();


    @ParameterizedTest
    @CsvSource({
            "karkowka,  12,  5",
            "karkowka,  12,  5",
            "kielbaska,  3,  1",
            "piwo,  2,  33",
            "ketchup,  6,  3",
            "ketchup,  6,  3",
            "chipsy,  7,  3",
    })
    void addCorrectProduct(String productName, int price, int quantity) {
        assertTrue(shoppingCart.addProducts(productName,price,quantity));
    }

    @ParameterizedTest
    @CsvSource({
            "karkowka,  -12,  5",
            "karkowka,  -12,  -5",
            "ketchup,  6,  501",
            "wegiel drzewny,  -6,  -3",
            " ketchup pikantny,  -6,  -3",
    })
    void addIncorrectProduct(String productName, int price, int quantity) {
        assertFalse(shoppingCart.addProducts(productName,price,quantity));
    }

    @ParameterizedTest
    @CsvSource({
            "karkowka,  12,  5, 2",
            "kielbaska,  3,  1, 1",
            "piwo,  2,  33, 15",
            "ketchup,  6,  3, 2",
            "chipsy,  6,  3, 1",
    })
    public void shouldDeleteProduct(String productName, int price, int quantity, int quantityDelete) {
        // given
        shoppingCart.addProducts(productName,price,quantity);
        // when
        boolean isDeleted = shoppingCart.deleteProducts(productName,quantityDelete);
        // then
        assertTrue(isDeleted);
    }

    @ParameterizedTest
    @CsvSource({
            "karkowka,  12,  5, 6",
            "kielbaska,  3,  1, -1",
            "piwo,  2,  33, 34",
    })
    public void shouldNotDeleteProduct(String productName, int price, int quantity, int quantityDelete) {
        // given
        shoppingCart.addProducts(productName,price,quantity);
        // when
        boolean isDeleted = shoppingCart.deleteProducts(productName,quantityDelete);
        // then
        assertFalse(isDeleted);
    }


    @Test
    public void shouldGetCorrectQuantity() {
        // given
        shoppingCart.addProducts("karkowka",12,5);
        shoppingCart.addProducts("karkowka",12,5);
        shoppingCart.addProducts("kielbaska",10,3);
        shoppingCart.addProducts("ketchup",10,2);
        // when
        int numberOfkarkowkaas = shoppingCart.getQuantityOfProduct("karkowka");
        int numberOfPear = shoppingCart.getQuantityOfProduct("kielbaska");
        int numberOfChocolate = shoppingCart.getQuantityOfProduct("ketchup");
        // then
        assertEquals(10, numberOfkarkowkaas);
        assertEquals(2, numberOfChocolate);
        assertEquals(3, numberOfPear);
    }
    @Test
    public void shouldGetCorrectPriceOfAllItems() {
        // given
        shoppingCart.addProducts("karkowka",10,5);
        shoppingCart.addProducts("karkowka",10,5);
        shoppingCart.addProducts("kielbaska",10,3);
        shoppingCart.addProducts("ketchup",10,2);
        // when
        int totalPrice = shoppingCart.getSumProductsPrices();
        // then
        assertEquals(150, totalPrice);
    }

    @ParameterizedTest
    @CsvSource({
            "karkowka,  12,  5",
            "karkowka,  12,  7",
            "kielbaska,  3,  1",
            "piwo,  2,  33",
            "ketchup,  6,  3",
            "chipsy,  6,  3",
    })
    void shouldGetCorretPrice(String productName, int price, int quantity) {
        // given
        shoppingCart.addProducts(productName,price,quantity);
        // when
        int priceOfItem = shoppingCart.getProductPrice(productName);
        // then
        assertEquals(price, priceOfItem);
    }

    @Test
    void shouldGetNameList() {
        // given
        List<String> givenList = Arrays.asList("karkowka", "kielbaska", "ketchup");
        shoppingCart.addProducts("karkowka",10,5);
        shoppingCart.addProducts("karkowka",10,5);
        shoppingCart.addProducts("kielbaska",10,3);
        shoppingCart.addProducts("ketchup",10,2);
        // when
        List<String> itemList = shoppingCart.getProductsNames();
        // then
        assertEquals(givenList, itemList);
    }


}

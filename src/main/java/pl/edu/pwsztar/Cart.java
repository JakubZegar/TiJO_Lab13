package pl.edu.pwsztar;

import java.util.List;

public interface Cart {

    List<Item> getAllProductsFromCart();

    boolean addProductToCart(String name, int amount, int price);

    boolean removeSomeAmountOfProductFromCart(String name, int amount);

    Item getItem(String name);
}

package pl.edu.pwsztar;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart implements ShoppingCartOperation {

    private Cart cart = new CartImpl();

    public boolean addProducts(String productName, int price, int quantity) {
        return cart.addProductToCart(productName,quantity,price);
    }

    public boolean deleteProducts(String productName, int quantity) {
        return cart.removeSomeAmountOfProductFromCart(productName,quantity);
    }

    public int getQuantityOfProduct(String productName) {
        return cart.getItem(productName).getAmount();
    }

    public int getSumProductsPrices() {
        return cart.getAllProductsFromCart().stream().mapToInt(total -> total.getPrice() * total.getAmount()).sum();
    }

    public int getProductPrice(String productName) {
        return cart.getItem(productName).getPrice();
    }

    public List<String> getProductsNames() {
        return cart.getAllProductsFromCart().stream().map(Item::getName).collect(Collectors.toList());
    }
}

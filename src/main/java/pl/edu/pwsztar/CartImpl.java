package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.List;

public class CartImpl implements Cart {

    private List<Item> shoppingCart = new ArrayList<>();
    private final static int TOTAL = 500;

    @Override
    public List<Item> getAllProductsFromCart() {
        return shoppingCart;
    }

    @Override
    public boolean addProductToCart(String name, int amount, int price) {
        if(price > 0 && amount > 0){
            if( getTotalAmountOfItems() + amount >= TOTAL ){
                    return false;
                } else if (shoppingCart.stream().anyMatch( item -> item.getName().equals(name) && item.getPrice() == price) && shoppingCart.size() > 0){
                    int index = findIndexOfItem(name);
                    shoppingCart.get(index).setAmount( shoppingCart.get(index).getAmount() + amount );
                    return true;
                } else if (shoppingCart.stream().anyMatch( item -> item.getName().equals(name) && item.getPrice() != price )) {
                    return false;
                } else {
                    shoppingCart.add(new Item(name,amount,price));
                    return true;
                }
        } else {
            return false;
        }
    }

    @Override
    public boolean removeSomeAmountOfProductFromCart(String name, int amount) {
        if ( amount > 0 ){
            int index = findIndexOfItem(name);

            if (shoppingCart.get(index).getAmount() == amount){
                shoppingCart.remove(index);
            } else if( shoppingCart.get(index).getAmount() < amount){
                return false;
            } else {
                shoppingCart.get(index).setAmount( shoppingCart.get(index).getAmount() - amount  );
            }

        } else {
            return false;
        }
        return true;
    }

    @Override
    public Item getItem(String name){
        return shoppingCart.get(findIndexOfItem(name));
    }

    private int findIndexOfItem(String name){
        for(int i =0; i< shoppingCart.size(); i++ ){
            if(shoppingCart.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    private int getTotalAmountOfItems(){
        if ( shoppingCart.size() > 0 ) {
            return shoppingCart.stream().mapToInt(Item::getAmount).sum();
        } else {
            return 0;
        }
    }
}
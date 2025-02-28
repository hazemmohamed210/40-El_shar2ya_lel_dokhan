package com.example.repository;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("rawtypes")
public class CartRepository extends MainRepository<Cart> {

    public static List<Cart> carts = new ArrayList<>();
    @Value("${spring.application.cartDataPath}")
    private String dataPath;

    @Override
    protected String getDataPath() {
        return dataPath;
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        return Cart[].class;
    }

    public Cart addCart(Cart cart){
        save(cart);
        return cart;
    }

    public ArrayList<Cart> getCarts(){
        return findAll();
    }

    public Cart getCartById(UUID cartId){
        List<Cart> filtered = findAll().stream().filter(cart -> cart.getId().equals(cartId)).toList();
        return (filtered.size() > 0)? filtered.get(0) : null;
    }

    public Cart getCartByUserId(UUID userId){
        List<Cart> filtered = findAll().stream().filter(cart -> cart.getUserId().equals(userId)).toList();
        return (filtered.size() > 0)? filtered.get(0) : null;
    }

    public void addProductToCart(UUID cartId, Product product){
        boolean added = false;
        List<Cart> allCarts = findAll();

        for(Cart cart : allCarts){
            if(cart.getId().equals(cartId)){
                cart.getProducts().add(product);
                added = true;
            }
        }

        if(added) saveAll((ArrayList<Cart>) allCarts);
    }

    public void deleteProductFromCart(UUID cartId, Product product){
        boolean removed = false;
        List<Cart> allCarts = findAll();

        for(Cart cart : allCarts){
            if(cart.getId().equals(cartId)){
                cart.getProducts().removeIf(prod -> prod.getId().equals(product.getId()));
                removed = true;
            }
        }

        if(removed) saveAll((ArrayList<Cart>) allCarts);
    }

    public void deleteCartById(UUID cartId){
        List<Cart> allCarts = findAll();
        allCarts = allCarts.stream().filter(cart -> !(cart.getId().equals(cartId))).collect(Collectors.toList());
        saveAll((ArrayList<Cart>) allCarts);
    }
}


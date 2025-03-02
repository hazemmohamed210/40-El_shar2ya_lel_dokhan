package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart>{

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCart(Cart cart){
        if(cart == null) throw new IllegalArgumentException();

        if(getCartById(cart.getId()) != null) return null;

        return cartRepository.addCart(cart);
    }

    public ArrayList<Cart> getCarts(){
        return cartRepository.getCarts();
    }

    public Cart getCartById(UUID cartId){
        if(cartId == null) throw new IllegalArgumentException();

        return cartRepository.getCartById(cartId);
    }

    public Cart getCartByUserId(UUID userId){
        if(userId == null) throw new IllegalArgumentException();

        return cartRepository.getCartByUserId(userId);
    }

    public void addProductToCart(UUID cartId, Product product){
        if(product == null) throw new IllegalArgumentException();

        cartRepository.addProductToCart(cartId, product);
    }

    public void deleteProductFromCart(UUID cartId, Product product){
        if(product == null || getCartById(cartId) == null) throw new IllegalArgumentException();

        cartRepository.deleteProductFromCart(cartId, product);
    }

    public void deleteCartById(UUID cartId){
        if(cartId == null || getCartById(cartId) == null) throw new IllegalArgumentException();

        cartRepository.deleteCartById(cartId);
    }
}


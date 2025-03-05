package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.Order;
import com.example.repository.UserRepository;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    public User addUser(User user) {
        if(user == null) throw new IllegalArgumentException();

        if(getUserById(user.getId()) != null) return null;

        return userRepository.addUser(user);
    }

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(UUID userId) {
        if(userId == null) throw new IllegalArgumentException();

        return userRepository.getUserById(userId);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        if(userId == null || getUserById(userId) == null) throw new IllegalArgumentException();

        return userRepository.getOrdersByUserId(userId);
    }

    public void addOrderToUser(UUID userId) {
        if(userId == null || getUserById(userId) == null) throw new IllegalArgumentException();

        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null || cart.getProducts().isEmpty()) {
            throw new IllegalStateException();
        }

        double totalPrice = cart.getProducts().stream().mapToDouble(Product::getPrice).sum();

        Order newOrder = new Order();
        newOrder.setId(UUID.randomUUID());
        newOrder.setProducts(new ArrayList<>(cart.getProducts()));
        newOrder.setTotalPrice(totalPrice);

        userRepository.addOrderToUser(userId, newOrder);

        cart.getProducts().clear();
        cartService.deleteCartById(cart.getId());
    }

    public void emptyCart(UUID userId){
        if(userId == null || getUserById(userId) == null) throw new IllegalArgumentException();

        Cart cart = cartRepository.getCartByUserId(userId);
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        if(userId == null || orderId == null) throw new IllegalArgumentException();

        userRepository.removeOrderFromUser(userId, orderId);
    }

    public void deleteUserById(UUID userId) {
        if(userId == null || getUserById(userId) == null) throw new IllegalArgumentException();

        userRepository.deleteUserById(userId);
    }
}

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
        return userRepository.addUser(user);
    }

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    public void addOrderToUser(UUID userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null || cart.getProducts().isEmpty()) {
            throw new IllegalStateException("Cart is empty or does not exist");
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
        Cart cart = cartRepository.getCartByUserId(userId);
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        userRepository.removeOrderFromUser(userId, orderId);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }
}

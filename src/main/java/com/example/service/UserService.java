package com.example.service;

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

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
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

    public void addOrderToUser(UUID userId, Order order) {
        userRepository.addOrderToUser(userId, order);
    }

    public void removeOrderFromUser(UUID userId, Order order) {
        userRepository.removeOrderFromUser(userId, order);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }
}

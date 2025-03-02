package com.example.controller;

import com.example.model.User;
import com.example.model.Order;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId) {
        return userService.getOrdersByUserId(userId);
    }

    @PostMapping("/{userId}/checkout")
    public void addOrderToUser(@PathVariable UUID userId, @RequestBody Order order) {
        userService.addOrderToUser(userId, order);
    }

    @PostMapping("/{userId}/removeOrder")
    public void removeOrderFromUser(@PathVariable UUID userId, @RequestBody Order order) {
        userService.removeOrderFromUser(userId, order);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
    }
}

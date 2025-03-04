package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository extends MainRepository<User> {

    @Value("${spring.application.userDataPath}")
    private String userDataPath;

    @Override
    protected String getDataPath() {
        return userDataPath;
    }

    @Override
    protected Class<User[]> getArrayType() {
        return User[].class;
    }

    public ArrayList<User> getUsers() {
        return findAll();
    }

    public User getUserById(UUID userId) {
        return getUsers().stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
    }

    public User addUser(User user) {
        save(user);
        return user;
    }

    public List<Order> getOrdersByUserId(UUID userId){
        User user = getUserById(userId);
        return (user != null)? user.getOrders():new ArrayList<>();
    }

    public void addOrderToUser(UUID userId, Order order){
        User user = getUserById(userId);
        if(user != null){
            user.getOrders().add(order);
            save(user);
        }
    }

    public void removeOrderFromUser(UUID userId, UUID orderId){
        User user = getUserById(userId);
        if(user != null){
            user.getOrders().removeIf(o -> o.getId().equals(orderId));
            save(user);
        }
    }

    public void deleteUserById(UUID userId){
        ArrayList<User> users = getUsers();
        users.removeIf(user -> user.getId().equals(userId));
        saveAll(users);
    }

}

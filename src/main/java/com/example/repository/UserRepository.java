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

    public void addOrderToUser(UUID userId, Order order) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if(user.getId().equals(userId)){
                user.getOrders().add(order);
                saveAll(users);
            }
        }
    }

    public void removeOrderFromUser(UUID userId, UUID orderId){
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.getOrders() == null) {
                user.setOrders(new ArrayList<>());
            }
            if(user.getId().equals(userId)){
                user.getOrders().removeIf(o -> o.getId().equals(orderId));
                saveAll(users);
            }
        }
    }

    public void deleteUserById(UUID userId){
        ArrayList<User> users = getUsers();
        users.removeIf(user -> user.getId().equals(userId));
        saveAll(users);
    }

}

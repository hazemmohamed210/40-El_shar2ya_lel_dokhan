//package com.example.MiniProject1;
//
//import com.example.model.User;
//import com.example.model.Order;
//import com.example.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////1- add new user done
////2- get the users done
////3- get a specific user done
////4- get the user's orders done
////5- add a new order done
////6- empty cart done
////7- remove order done
////8- delete the user done
//
//@SpringBootTest
//public class UserTests {
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void addUser_withValidInput_shouldReturnSameId() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//
//        User created = userService.addUser(user);
//
//        assertEquals(user.getId(), created.getId());
//    }
//    @Test
//    public void addUser_withExistingId_shouldReturnNull() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//
//        userService.addUser(user);
//        User existing = userService.addUser(user);
//
//        assertNull(existing);
//    }
//    @Test
//    public void addUser_withNoUser_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.addUser(null));
//    }
//
//    @Test
//    public void getUsers_withOneUser_shouldReturnUser() {
//        userService.addUser(new User(UUID.randomUUID(), "Dummy User", new ArrayList<>()));
//
//        ArrayList<User> users = userService.getUsers();
//
//        assertFalse(users.isEmpty());
//    }
//    @Test
//    public void getUsers_withMultipleUsers_shouldReturnSameSize() {
//        userService.addUser(new User(UUID.randomUUID(), "Dummy User 1", new ArrayList<>()));
//        userService.addUser(new User(UUID.randomUUID(), "Dummy User 2", new ArrayList<>()));
//
//        ArrayList<User> users = userService.getUsers();
//
//        assertTrue(users.size() >= 2);
//    }
//    @Test
//    public void getUsers_withNoUsers_shouldReturnEmpty() {
//        ArrayList<User> users = userService.getUsers();
//
//        assertTrue(users.isEmpty());
//    }
//
//    @Test
//    public void getUserById_withValidInput_shouldReturnUserId() {
//        User user = new User(UUID.randomUUID(), "Dummy User 1", new ArrayList<>());
//        userService.addUser(user);
//
//        User created = userService.getUserById(user.getId());
//
//        assertEquals(user.getId(), created.getId());
//    }
//    @Test
//    public void getUserById_withInvalidId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//        User user = userService.getUserById(randomId);
//
//        assertNull(user);
//    }
//    @Test
//    public void getUserById_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(null));
//    }
//
//    @Test
//    public void getOrdersByUserId_withValidInput_shouldReturnOrders() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 74.9, new ArrayList<>());
//        user.getOrders().add(order);
//        userService.addUser(user);
//
//        ArrayList<Order> orders = userService.getOrdersByUserId(user.getId());
//
//        assertTrue(orders.contains(order));
//    }
//    @Test
//    public void getOrdersByUserId_withInvalidId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//        User user = userService.getOrdersByUserId(randomId);
//
//        assertNull(user);
//    }
//    @Test
//    public void getOrdersByUserId_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.getOrdersByUserId(null));
//    }
//
//    @Test
//    public void addOrderToUser_withValidInput_shouldReturnOrder() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        userService.addUser(user);
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 74.9, new ArrayList<>());
//        user.getOrders().add(order);
//
//        userService.addOrderToUser(user.getId());
//        User updated = userService.getUserById(user.getId());
//
//        assertTrue(updated.getOrders().contains(order));
//    }
//    @Test
//    public void addOrderToUser_withInvalidId_shouldReturnException() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 74.9, new ArrayList<>());
//        UUID randomId = UUID.randomUUID();
//
//        assertThrows(IllegalArgumentException.class, () -> userService.addOrderToUser(randomId));
//    }
//    @Test
//    public void addOrderToUser_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.addOrderToUser(null));
//    }
//
//    @Test
//    public void emptyCart_withValidInput_shouldReturnTrue() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        userService.addUser(user);
//
//        userService.emptyCart(user);
//        ArrayList<Order> orders = userService.getOrdersByUserId(user.getId());
//
//        assertTrue(orders.isEmpty());
//    }
//    @Test
//    public void emptyCart_withInvalidId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//         userService.emptyCart(randomId);
//         ArrayList<Order> orders = userService.getOrdersByUserId(randomId);
//
//        assertNull(orders);
//    }
//    @Test
//    public void emptyCart_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.emptyCart(null));
//    }
//
//    @Test
//    public void removeOrderFromUser_withValidInput_shouldReturnRemoved() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 74.9, new ArrayList<>());
//        user.getOrders().add(order);
//        userService.addUser(user);
//
//        userService.removeOrderFromUser(user.getId(), order.getId());
//        ArrayList<Order> orders = userService.getOrdersByUserId(user.getId());
//
//        assertFalse(orders.contains(order));
//    }
//    @Test
//    public void removeOrderFromUser_withNoId_shouldReturnException() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 74.9, new ArrayList<>());
//
//        assertThrows(IllegalArgumentException.class, () -> userService.removeOrderFromUser(null, order.getId()));
//    }
//    @Test
//    public void removeOrderFromUser_withNoOrder_shouldReturnException() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        userService.addUser(user);
//
//        assertThrows(IllegalArgumentException.class, () -> userService.removeOrderFromUser(user.getId(), null));
//    }
//
//    @Test
//    public void deleteUserById_withValidInput_shouldReturnNull() {
//        User user = new User(UUID.randomUUID(), "Dummy User", new ArrayList<>());
//        userService.addUser(user);
//
//        userService.deleteUserById(user.getId());
//        User updated = userService.getUserById(user.getId());
//
//        assertNull(updated);
//    }
//    @Test
//    public void deleteUserById_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserById(null));
//    }
//    @Test
//    public void deleteUserById_withInvalidId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserById(UUID.randomUUID()));
//    }
//
//    @Test
//    void contextLoads() {
//    }
//}

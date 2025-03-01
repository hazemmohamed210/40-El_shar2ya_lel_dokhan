//package com.example.MiniProject1;
//
//import com.example.model.Order;
//import com.example.service.OrderService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////22- add order done
////23- get all orders done
////24- get a specific order done
////25- delete a specific order done
//
//@SpringBootTest
//public class OrderTests {
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    public void addOrder_withValidInput_shouldReturnSameId() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>());
//
//        Order created = orderService.addOrder(order);
//
//        assertEquals(order.getId(), created.getId());
//    }
//    @Test
//    public void addOrder_withExistingId_shouldReturnNull() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>());
//
//        orderService.addOrder(order);
//        Order existing = orderService.addOrder(order);
//
//        assertNull(existing);
//    }
//    @Test
//    public void addOrder_withNoOrder_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(null));
//    }
//
//    @Test
//    public void getOrders_withValidInput_shouldReturnOrder() {
//        orderService.addOrder(new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>()));
//
//        ArrayList<Order> orders = orderService.getOrders();
//
//        assertFalse(orders.isEmpty());
//    }
//    @Test
//    public void getOrders_withMultipleOrders_shouldReturnSameSize() {
//        orderService.addOrder(new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>()));
//        orderService.addOrder(new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>()));
//
//        ArrayList<Order> orders = orderService.getOrders();
//
//        assertTrue(orders.size() >= 2);
//    }
//    @Test
//    public void getOrders_withNoOrders_shouldReturnEmpty() {
//        ArrayList<Order> orders = orderService.getOrders();
//
//        assertTrue(orders.isEmpty());
//    }
//
//    @Test
//    public void getOrderById_withValidInput_shouldReturnOrder() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>());
//        orderService.addOrder(order);
//
//        Order created = orderService.getOrderById(order.getId());
//
//        assertEquals(order.getId(), created.getId());
//    }
//    @Test
//    public void getOrderById_withInvalidId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//        Order order = orderService.getOrderById(randomId);
//
//        assertNull(order);
//    }
//    @Test
//    public void getOrderById_withNoOrder_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> orderService.getOrderById(null));
//    }
//
//    @Test
//    public void deleteOrderById_withValidInput_shouldReturnNull() {
//        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), 41.6, new ArrayList<>());
//        orderService.addOrder(order);
//
//        orderService.deleteOrderById(order.getId());
//        Order updated = orderService.getOrderById(order.getId());
//
//        assertNull(updated);
//    }
//    @Test
//    public void deleteOrderById_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(null));
//    }
//    @Test
//    public void deleteOrderById_withInvalidId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(UUID.randomUUID()));
//    }
//
//    @Test
//    void contextLoads() {
//    }
//}
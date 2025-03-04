package com.example.service;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order){
        if(order == null) throw new IllegalArgumentException();
        if(getOrderById(order.getId()) != null) return;
        orderRepository.addOrder(order);
    }

    public ArrayList<Order> getOrders(){
        return orderRepository.getOrders();
    }

    public Order getOrderById(UUID orderId){return orderRepository.getOrderById(orderId); }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException{orderRepository.deleteOrderById(orderId); }


}
package com.example.repository;

import com.example.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {

    public static List<Order> orders = new ArrayList<>();
    @Value("${spring.application.orderDataPath}")
    private String dataPath;

    @Override
    protected String getDataPath() {return dataPath; }

    @Override
    protected Class<Order[]> getArrayType() {return Order[].class; }

    public void addOrder(Order order){
        orders.add(order);
    }

    public ArrayList<Order> getOrders(){
        return findAll();
    }

    public Order getOrderById(UUID orderId){
        List<Order> filteredOrders = findAll().stream().filter(order -> order.getId().equals(orderId)).toList();
        return filteredOrders.isEmpty() ? null : filteredOrders.get(0);
    }

    public void deleteOrderById(UUID orderId){
        List<Order> allOrders = findAll();
        allOrders = allOrders.stream().filter(order -> !(order.getId().equals(orderId))).collect(Collectors.toList());
        saveAll((ArrayList<Order>) allOrders);
    }
}

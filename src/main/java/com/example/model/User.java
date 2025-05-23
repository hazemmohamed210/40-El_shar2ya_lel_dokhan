package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Component
@Data
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private List<Order> orders;

    public User() {
        this.orders = new ArrayList<>();
    }

    public User(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }
}

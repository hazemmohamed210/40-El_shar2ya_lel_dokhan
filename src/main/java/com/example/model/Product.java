package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private double price;

    public Product(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
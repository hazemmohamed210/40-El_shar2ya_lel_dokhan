package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductService extends MainService<Product> {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        if(product == null) throw new IllegalArgumentException();

        if(getProductById(product.getId()) != null) return null;

        return productRepository.addProduct(product);
    }


    public ArrayList<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductById(UUID productId) {
        if(productId == null) throw new IllegalArgumentException();
        return productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (newPrice < 0) {
            throw new IllegalArgumentException();
        }

        return productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException();
        }
        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
        if(productId == null || getProductById(productId) == null) throw new IllegalArgumentException();

        productRepository.deleteProductById(productId);
    }
}

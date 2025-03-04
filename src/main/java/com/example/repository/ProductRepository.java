package com.example.repository;

import com.example.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {

    public static List<Product> products = new ArrayList<>();
    @Value("${spring.application.productDataPath}")
    private String dataPath;

    @Override
    protected String getDataPath() {
        return dataPath;
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }

    public Product addProduct(Product product) {
        List<Product> products = getProducts();
        products.add(product);
        save(product);
        return product;
    }

    public ArrayList<Product> getProducts() {
        return findAll();
    }

    public Product getProductById(UUID productId) {
        List<Product> filtered = findAll().stream().filter(product -> product.getId().equals(productId)).toList();
        return (filtered.size() > 0)? filtered.get(0) : null;
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setName(newName);
                product.setPrice(newPrice);
                //save(Product);
                return product;
            }
        }
        return null;
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (productIds.contains(product.getId())) {
                double newPrice = product.getPrice() * (1 - discount / 100);
                product.setPrice(newPrice);
            }
        }
       // save(Product);
    }

    public void deleteProductById(UUID productId) {
        List<Product> deletedProduct = findAll();
        deletedProduct = deletedProduct.stream().filter(product -> !(product.getId().equals(productId))).collect(Collectors.toList());
        saveAll((ArrayList<Product>) deletedProduct);
    }
}

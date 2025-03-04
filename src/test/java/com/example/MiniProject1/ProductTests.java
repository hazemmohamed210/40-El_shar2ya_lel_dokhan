package com.example.MiniProject1;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//9- add new product done
//10- get all products done
//11- get a specific product done
//12- update a product done
//13- apply discount done
//14- delete a product done

@SpringBootTest
public class ProductTests {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void addProduct_withValidInput_shouldReturnSameId() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);

        Product created = productService.addProduct(product);

        assertEquals(product.getId(), created.getId());
    }
    @Test
    public void addProduct_withExistingId_shouldReturnNull() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);

        productService.addProduct(product);
        Product existing = productService.addProduct(product);

        assertNull(existing);
    }
    @Test
    public void addProduct_withNoProduct_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(null));
    }

    @Test
    public void getProducts_withOneProduct_shouldReturnProduct() {
        productService.addProduct(new Product(UUID.randomUUID(), "Dummy Product", 150.3));

        ArrayList<Product> products = productService.getProducts();

        assertFalse(products.isEmpty());
    }
    @Test
    public void getProducts_withMultipleProducts_shouldReturnSameSize() {
        productService.addProduct(new Product(UUID.randomUUID(), "Dummy Product", 150.3));
        productService.addProduct(new Product(UUID.randomUUID(), "Dummy Product", 150.3));

        ArrayList<Product> products = productService.getProducts();

        assertTrue(products.size() >= 2);
    }
    @Test
    public void getProducts_withNoProduct_shouldReturnEmpty() {
        productRepository.overrideData(new ArrayList<>());
        ArrayList<Product> products = productService.getProducts();

        assertTrue(products.isEmpty());
    }

    @Test
    public void getProductById_withValidInput_shouldReturnProduct() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
        productService.addProduct(product);

        Product created = productService.getProductById(product.getId());

        assertEquals(product.getId(), created.getId());
    }
    @Test
    public void getProductById_withInvalidProductId_shouldReturnNull() {
        UUID randomId = UUID.randomUUID();

        Product product = productService.getProductById(randomId);

        assertNull(product);
    }
    @Test
    public void getProductById_withNoProduct_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(null));
    }

    @Test
    public void updateProduct_withValidInput_shouldReturnSameIdDiffName() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
        productService.addProduct(product);

        Product updated = productService.updateProduct(product.getId(), "Updated", 82.6);

        assertEquals(product.getId(), updated.getId());
        assertNotEquals(product.getName(), updated.getName());
    }
    @Test
    public void updateProduct_withInvalidId_shouldReturnNull() {
        UUID randomId = UUID.randomUUID();

        Product updated = productService.updateProduct(randomId, "Updated", 82.6);

        assertNull(updated);
    }
    @Test
    public void updateProduct_withNoProduct_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(null, "Updated", 82.6));
    }

    @Test
    public void applyDiscount_withValidInput_shouldReturnDiscount() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
        productService.addProduct(product);
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(product.getId());

        productService.applyDiscount(40.0, productIds);
        Product updated = productService.getProductById(product.getId());

        assertNotEquals(product.getPrice(), updated.getPrice());
    }
    @Test
    public void applyDiscount_withInvalidProductId_shouldReturnNull() {
        UUID randomId = UUID.randomUUID();
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(randomId);

        productService.applyDiscount(40.0, productIds);
        Product product = productService.getProductById(randomId);

        assertNull(product);
    }
    @Test
    public void applyDiscount_withInvalidDiscount_shouldReturnSamePrice() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
        productService.addProduct(product);
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(product.getId());

        productService.applyDiscount(-10.0, productIds);
        Product updated = productService.getProductById(product.getId());

        assertEquals(product.getPrice(), updated.getPrice());
    }

    @Test
    public void deleteProductById_withValidInput_shouldReturnNull() {
        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
        productService.addProduct(product);

        productService.deleteProductById(product.getId());
        Product updated = productService.getProductById(product.getId());

        assertNull(updated);
    }
    @Test
    public void deleteProductById_withNoId_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProductById(null));
    }
    @Test
    public void deleteProductById_withInvalidId_shouldReturnException() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProductById(UUID.randomUUID()));
    }

    @Test
    void contextLoads() {
    }
}

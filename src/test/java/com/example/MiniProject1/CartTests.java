//package com.example.MiniProject1;
//
//import com.example.model.Cart;
//import com.example.model.Product;
//import com.example.service.CartService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////15- add cart done
////16- get all carts done
////17- get a specific cart done
////18- get a user's cart done
////19- add product to the cart done
////20- delete product from the cart done
////21- delete the cart done
//
//@SpringBootTest
//public class CartTests {
//    @Autowired
//    private CartService cartService;
//
//    @Test
//    public void addCart_withValidCartId_shouldReturnSameId() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//
//        Cart created = cartService.addCart(cart);
//
//        assertEquals(cart.getId(), created.getId());
//    }
//    @Test
//    public void addCart_withExistingCartId_shouldReturnNull() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//
//        cartService.addCart(cart);
//        Cart existing = cartService.addCart(cart);
//
//        assertNull(existing);
//    }
//    @Test
//    public void addCart_withNoCart_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> cartService.addCart(null));
//    }
//
//    @Test
//    public void getCarts_withOneCart_shouldReturnCart() {
//        cartService.addCart(new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>()));
//
//        ArrayList<Cart> carts = cartService.getCarts();
//
//        assertFalse(carts.isEmpty());
//    }
//    @Test
//    public void getCarts_withMultipleCarts_shouldReturnSameSize() {
//        cartService.addCart(new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>()));
//        cartService.addCart(new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>()));
//
//        ArrayList<Cart> carts = cartService.getCarts();
//
//        assertTrue(carts.size() >= 2);
//    }
//    @Test
//    public void getCarts_withNoCarts_shouldReturnEmpty() {
//        ArrayList<Cart> carts = cartService.getCarts();
//
//        assertTrue(carts.isEmpty());
//    }
//
//    @Test
//    public void getCartById_withValidCartId_shouldReturnSameId() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//
//        Cart created = cartService.getCartById(cart.getId());
//
//        assertEquals(cart.getId(), created.getId());
//    }
//    @Test
//    public void getCartById_withInvalidCartId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//        Cart cart = cartService.getCartById(randomId);
//
//        assertNull(cart);
//    }
//    @Test
//    public void getCartById_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> cartService.getCartById(null));
//    }
//
//    @Test
//    public void getCartByUserId_withValidInput_shouldReturnSameId() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//
//        Cart created = cartService.getCartByUserId(cart.getUserId());
//
//        assertEquals(cart.getId(),created.getId());
//    }
//    @Test
//    public void getCartByUserId_withInvalidUserId_shouldReturnNull() {
//        UUID randomId = UUID.randomUUID();
//
//        Cart cart = cartService.getCartByUserId(randomId);
//
//        assertNull(cart);
//    }
//    @Test
//    public void getCartByUserId_withNoUserId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> cartService.getCartByUserId(null));
//    }
//
//    @Test
//    public void addProductToCart_withValidInput_shouldReturnProduct() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
//        cart.getProducts().add(product);
//
//        cartService.addProductToCart(cart.getId(), product);
//        Cart updated = cartService.getCartById(cart.getId());
//
//        assertTrue(updated.getProducts().contains(product));
//    }
//    @Test
//    public void addProductToCart_withInvalidId_shouldReturnNull() {
//        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
//        UUID randomId = UUID.randomUUID();
//
//        cartService.addProductToCart(randomId, product);
//
//        Cart cart = cartService.getCartById(randomId);
//        assertNull(cart);
//    }
//    @Test
//    public void addProductToCart_withNoProduct_shouldReturnException() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//
//        assertThrows(IllegalArgumentException.class, () -> cartService.addProductToCart(cart.getId(), null));
//    }
//
//    @Test
//    public void deleteProductFromCart_withValidInput_shouldReturnDeletedProduct() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
//        cart.getProducts().add(product);
//        cartService.addCart(cart);
//
//        cartService.deleteProductFromCart(cart.getId(), product);
//        Cart updated = cartService.getCartById(cart.getId());
//
//        assertFalse(updated.getProducts().contains(product));
//    }
//    @Test
//    public void deleteProductFromCart_withNoProduct_shouldReturnException() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//
//        assertThrows(IllegalArgumentException.class, () -> cartService.deleteProductFromCart(cart.getId(), null));
//    }
//    @Test
//    public void deleteProductFromCart_withInvalidId_shouldReturnException() {
//        Product product = new Product(UUID.randomUUID(), "Dummy Product", 150.3);
//
//        assertThrows(IllegalArgumentException.class, () -> cartService.deleteProductFromCart(UUID.randomUUID(), product));
//    }
//
//    @Test
//    public void deleteCartById_withValidInput_shouldReturnNull() {
//        Cart cart = new Cart(UUID.randomUUID(), UUID.randomUUID(), new ArrayList<>());
//        cartService.addCart(cart);
//
//        cartService.deleteCartById(cart.getId());
//        Cart updated = cartService.getCartById(cart.getId());
//
//        assertNull(updated);
//    }
//    @Test
//    public void deleteCartById_withNoId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> cartService.deleteCartById(null));
//    }
//    @Test
//    public void deleteCartById_withInvalidId_shouldReturnException() {
//        assertThrows(IllegalArgumentException.class, () -> cartService.deleteCartById(UUID.randomUUID()));
//    }
//
//    @Test
//    void contextLoads() {
//    }
//}
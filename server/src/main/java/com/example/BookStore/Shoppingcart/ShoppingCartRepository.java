package com.example.BookStore.Shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByUserUsername(String username);
}
// This repository interface extends JpaRepository to provide CRUD operations for ShoppingCartItem entities.
// It also includes a custom method to find all shopping cart items by user ID.
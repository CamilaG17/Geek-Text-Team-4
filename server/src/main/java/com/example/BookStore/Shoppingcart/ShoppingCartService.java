package com.example.BookStore.Shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStore.BookDetails.Book;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    // Get subtotal of the shopping cart
    public double calculateCartSubtotal(Long userId) {
        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        return cartItems.stream()
                        .mapToDouble(item -> item.getBook().getprice() * item.getQuantity())
                        .sum();
    }

    // Get books in the shopping cart
    public List<Book> getBooksInCart(Long userId) {
        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        return cartItems.stream()
                        .map(ShoppingCartItem::getBook)
                        .toList();
    }
}

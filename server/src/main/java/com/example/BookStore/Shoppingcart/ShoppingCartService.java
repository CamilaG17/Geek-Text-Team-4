package com.example.BookStore.Shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStore.BookDetails.Book;
import com.example.BookStore.BookDetails.Bookrepository;
import com.example.BookStore.UserManagement.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Bookrepository bookRepository;

    
    public double calculateCartSubtotal(String username) {
        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUsername(username);
        System.out.println("Found " + cartItems.size() + " cart items for user: " + username);
        
        double total = 0.0;
        for (ShoppingCartItem item : cartItems) {
            
            Book book = bookRepository.findByIsbn(item.getIsbn()).orElse(null);
            
            if (book != null) {
                double price = book.getprice();
                int quantity = item.getQuantity();
                System.out.println("Book: " + book.getBookName() + ", Price: " +price + ", Quantity: " + item.getQuantity());
                total += price * quantity;
            }
        }
        return total;
    }

    
    public List<Map<String, Object>> getBooksInCart(String username) { // Included returning quantity for each book
    List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUsername(username);
    List<Map<String, Object>> booksWithQuantity = new ArrayList<>();
    
    for (ShoppingCartItem item : cartItems) {
        bookRepository.findByIsbn(item.getIsbn())
            .ifPresent(book -> {
                Map<String, Object> bookMap = new HashMap<>();
                bookMap.put("book", book);
                bookMap.put("quantity", item.getQuantity());
                booksWithQuantity.add(bookMap);
            });
    }
    return booksWithQuantity;
}

    public void addToCart(String username, Long isbn) {
        System.out.println("🔍 addToCart called with userId = " + username + ", bookId = " + isbn);

        
        userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    
       
        List<ShoppingCartItem> existingItems = shoppingCartRepository.findByUsernameAndIsbn(username, isbn);
        
        if (!existingItems.isEmpty()) {
            
            ShoppingCartItem item = existingItems.get(0);
            item.setQuantity(item.getQuantity() + 1);
            shoppingCartRepository.save(item);
        } else {
            
            ShoppingCartItem item = new ShoppingCartItem();
            item.setUsername(username);
            item.setIsbn(isbn);
            item.setQuantity(1);
            shoppingCartRepository.save(item);
        }
    }
}
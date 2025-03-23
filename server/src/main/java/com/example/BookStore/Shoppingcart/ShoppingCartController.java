package com.example.BookStore.Shoppingcart;

import org.springframework.web.bind.annotation.*;
import com.example.BookStore.BookDetails.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React app
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // 1. Get the subtotal of the books in the shopping cart for a user
    @GetMapping("/{username}/subtotal")
    public Map<String, Double> getCartSubtotal(@PathVariable String username) {
        double subtotal = shoppingCartService.calculateCartSubtotal(username);
        Map<String, Double> response = new HashMap<>();
        response.put("subtotal", subtotal);
        return response;
    }

    // 2. Get the list of books in the shopping cart for a user
    @GetMapping("/{username}/books")
    public List<Book> getBooksInCart(@PathVariable String username) {
        return shoppingCartService.getBooksInCart(username);
    }

    // 3. Add a book to the shopping cart
    @PostMapping("/add")
    public ResponseEntity<?> addBookToCart(@RequestParam String userId, @RequestParam Long bookId) {
        shoppingCartService.addToCart(userId, bookId);
        return ResponseEntity.ok().build();
    }
}

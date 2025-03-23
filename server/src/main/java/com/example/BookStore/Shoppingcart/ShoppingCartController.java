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
        double taxRate = 0.07; // 7% tax
        double tax = subtotal * taxRate;
        double total = subtotal + tax;
    
        Map<String, Double> response = new HashMap<>();
        response.put("subtotal", round(subtotal));
        response.put("tax", round(tax));
                response.put("total", round(total));
            
                return response;
            }
        
            private Double round(double tax) {
                throw new UnsupportedOperationException("Unimplemented method 'round'");
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

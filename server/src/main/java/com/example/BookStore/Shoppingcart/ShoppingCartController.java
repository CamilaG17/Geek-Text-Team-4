package com.example.BookStore.Shoppingcart;

import org.springframework.web.bind.annotation.*;
import com.example.BookStore.BookDetails.Book;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;

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
    
        Map<String, Double> response = new LinkedHashMap<>();
        response.put("subtotal", round(subtotal));
        response.put("tax", round(tax));
        response.put("total", round(total));
       
        return response;
           
        
            }
        
            private Double round(double tax) {
                return Math.round(tax* 100.0) / 100.0;
            }
        
            // 2. Get the list of books in the shopping cart for a user
    @GetMapping("/{username}/books")
   public List<Map<String, Object>> getBooksInCart(@PathVariable String username, Authentication auth) throws AccessDeniedException {
    if (!auth.name().equals(username)) {
        throw new AccessDeniedException("You can only view your own cart.");
    }
    return shoppingCartService.getBooksInCart(username);
    }

    // 3. Add a book to the shopping cart
    @PostMapping("/add")
    public ResponseEntity<?> addBookToCart(@RequestBody Map<String, Object> request, Authentication auth) throws AccessDeniedException {
        String username = (String) request.get("username");
        if (!auth.name().equals(username)) {
            throw new AccessDeniedException("You can only add to your own cart.");
        }
        Long isbn = Long.parseLong(request.get("isbn").toString());
        shoppingCartService.addToCart(username, isbn);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFromCart(@RequestBody Map<String, Object> request) {
    String username = (String) request.get("username");
    Long isbn = Long.parseLong(request.get("isbn").toString());
    shoppingCartService.deleteFromCart(username, isbn);
    return ResponseEntity.ok().build();
}
}

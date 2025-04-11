/*package com.example.BookStore.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/makeWishlist")
    public ResponseEntity<?> postDetails(@RequestBody Wishlist wishlist) {
        try {
            // Debug log
            System.out.println("Received wishlist: " + 
                "wishlistid=" + wishlist.getWishlistid() + 
                ", isbn=" + wishlist.getIsbn() + 
                ", username=" + wishlist.getUsername());
            
            if (wishlist.getIsbn() == null || wishlist.getUsername() == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "ISBN and username are required"));
            }
    
            Wishlist savedWishlist = wishlistService.saveDetails(wishlist);
            
            return ResponseEntity.ok(Map.of(
                "message", "Wishlist created successfully",
                "wishlist", savedWishlist
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
}

 */

package com.example.BookStore.Wishlist;

import com.example.BookStore.BookDetails.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Endpoint to create a new wishlist
    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String username = body.get("username");
        return ResponseEntity.ok(wishlistService.createWishlist(name, username));
    }

    // Endpoint to add a book to a wishlist
    @PostMapping("/add")
    public ResponseEntity<Void> addBook(@RequestBody Map<String, String> body) {
        Long wishlistId = Long.parseLong(body.get("wishlistId"));
        Long isbn = Long.parseLong(body.get("isbn"));
        wishlistService.addBookToWishlist(wishlistId, isbn);
        return ResponseEntity.status(201).build(); // HTTP 201 for created
    }

    // Endpoint to remove a book from the wishlist and add it to the cart
    @DeleteMapping("/remove-and-add-to-cart")
    public ResponseEntity<Void> removeAndAddToCart(@RequestBody Map<String, String> body) {
        Long wishlistId = Long.parseLong(body.get("wishlistId"));
        Long isbn = Long.parseLong(body.get("isbn"));
        String username = body.get("username");
        wishlistService.removeBookAndAddToCart(wishlistId, isbn, username);
        return ResponseEntity.status(204).build(); // HTTP 204 for no content
    }

    // Endpoint to get all books in a specific wishlist
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam Long wishlistId) {
        return ResponseEntity.ok(wishlistService.getBooksInWishlist(wishlistId));
    }
}

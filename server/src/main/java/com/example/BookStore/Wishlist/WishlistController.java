package com.example.BookStore.Wishlist;

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
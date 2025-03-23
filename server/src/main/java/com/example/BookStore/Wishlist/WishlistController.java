package com.example.BookStore.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @PostMapping("/makeWishlist")
    public Wishlist postDetails(@RequestBody Wishlist wishlist){

        return wishlistService.saveDetails(wishlist);

    }
    @GetMapping("/getWishlist")
    public List<Wishlist> getDetails(){
        return wishlistService.getAllDetails();

    }
}

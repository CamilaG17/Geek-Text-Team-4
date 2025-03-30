package com.example.BookStore.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    public Wishlist saveDetails(Wishlist wishlist) {

        return wishlistRepo.save(wishlist);

    }

    public List<Wishlist> getAllDetails() {
        return wishlistRepo.findAll();
    }

}


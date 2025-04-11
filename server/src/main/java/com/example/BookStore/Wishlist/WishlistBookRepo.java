package com.example.BookStore.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishlistBookRepo extends JpaRepository<WishlistBook, Long> {
    List<WishlistBook> findByWishlistId(Long wishlistId);  // Find books in a wishlist
    void deleteByWishlistIdAndBookIsbn(Long wishlistId, Long isbn);  // Remove book from wishlist
}





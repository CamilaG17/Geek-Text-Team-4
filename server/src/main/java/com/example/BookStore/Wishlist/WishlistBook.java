package com.example.BookStore.Wishlist;

import com.example.BookStore.BookDetails.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  // This generates the getter/setter methods automatically
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist_books")
public class WishlistBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID for each WishlistBook entry

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;  // The wishlist this book belongs to

    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;  // The book associated with this wishlist entry

    // Manually adding setter for 'wishlist'
    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    // Manually adding setter for 'book'
    public void setBook(Book book) {
        this.book = book;
    }

    // Manually adding getter for 'book'
    public Book getBook() {
        return book;
    }


}


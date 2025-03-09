package com.example.BookStore.Shoppingcart;

import com.example.BookStore.UserManagement.User;
import com.example.BookStore.BookDetails.Book;

import jakarta.persistence.*;

@Entity
public class ShoppingCartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookname", nullable = false)
    private Book book;

    private int quantity;

    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

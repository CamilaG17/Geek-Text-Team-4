package com.example.BookStore.Shoppingcart;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppingcart") 
public class ShoppingCartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    
    @Column(name = "isbn")
    private Long isbn;  
    
    @Column(name = "quantity")
    private int quantity;
    
    // Default constructor
    public ShoppingCartItem() {
    }
    
    // Parameterized constructor
    public ShoppingCartItem(String username, Long isbn, int quantity) {
        this.username = username;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getIsbn() { return isbn; }
    public void setIsbn(Long isbn) { this.isbn = isbn; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
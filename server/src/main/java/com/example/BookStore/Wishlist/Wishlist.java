package com.example.BookStore.Wishlist;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @Column(name = "wishlistid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistid;  
    private Long isbn;      

    @Column(name = "username")
    private String username; 
}
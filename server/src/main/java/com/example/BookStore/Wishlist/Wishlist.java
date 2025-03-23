package com.example.BookStore.Wishlist;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "wishlistid")
@NoArgsConstructor
@AllArgsConstructor

public class Wishlist {
    @Id
    @Column(name = "wishlistid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;

    @Column(name = "isbn")
    private String bookId;

    @Column(name = "username")
    private String userId;


}
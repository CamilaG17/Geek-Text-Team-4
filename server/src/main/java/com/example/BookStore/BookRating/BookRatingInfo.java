package com.example.BookStore.BookRating;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "bookrating")
public class BookRatingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Added proper primary key
    
    @Column(name = "isbn")
    private Long bookID;
    
    @Column(name = "username")
    private String userID;
    
    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "comment")
    private String comment;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }
    
    public Long getBookID() {
        return bookID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public Integer getRating() {
        return rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return comment;
    }
}
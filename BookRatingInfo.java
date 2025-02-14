package com.example.rateAndComment.bookstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "book_Rating")
public class BookRatingInfo {
    @Id
    @Column(name = "bookid")
    private long bookID;
    @Column(name = "userid")
    private long userID;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

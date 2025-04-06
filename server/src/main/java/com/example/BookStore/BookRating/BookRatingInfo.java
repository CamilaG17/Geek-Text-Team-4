package com.example.BookStore.BookRating;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "bookrating") // Changed the table name to match shared database
public class BookRatingInfo {
    @Id
    @Column(name = "isbn") // Changed the column name along with the type to match shared database.
    private Long bookID;
    @Column(name = "username")// Changed the column name along with the type to match shared database.
    private String userID;
    @Column(name = "rating")// Changed the column name along with the type to match shared database.
    private Integer rating;
    @Column(name = "comment")// Changed the column name along with the type to match shared database.
    private String comment;

    //Added get methods for the variables
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

    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getRating() {
        return rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
}

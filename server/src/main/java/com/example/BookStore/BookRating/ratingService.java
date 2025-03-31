package com.example.BookStore.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class ratingService {
    @Autowired
    private BookRatingInfoRepo bookRepo;

    public void postRating(long bookID, String userID, int rating){
        List<BookRatingInfo> bookRating = bookRepo.findByBookIDAndUserID(bookID, userID);
        
        BookRatingInfo ratingInfo = bookRating.get(0);
        ratingInfo.setRating(rating);
    }

    public void postComment(long bookID, String userID, String comment){
        List<BookRatingInfo> bookRating = bookRepo.findByBookIDAndUserID(bookID, userID);
        
        BookRatingInfo commentInfo = bookRating.get(0);
        commentInfo.setComment(comment);
    }

    public List<String> getAllComment(long bookID){
        List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
        return bookRating.stream()
            .map(BookRatingInfo :: getComment)
            .toList();
    }

    public OptionalDouble getRatingAvg(long bookID){
        List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
        return bookRating.stream()
            .mapToDouble(item -> item.getRating())
            .average();
    }

    
}

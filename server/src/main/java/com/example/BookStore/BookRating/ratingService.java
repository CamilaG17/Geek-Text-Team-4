package com.example.BookStore.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

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

    public List<Map<String, Object>> getAllCommentWithUsername(long bookID) {
        List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
        return bookRating.stream()
            .filter(info -> info.getComment() != null && !info.getComment().isEmpty())
            .map(info -> {
                Map<String, Object> commentData = new HashMap<>();
                commentData.put("text", info.getComment());
                commentData.put("username", info.getUserID());
                return commentData;
            })
            .collect(Collectors.toList());
    }

    public OptionalDouble getRatingAvg(long bookID) {
        try {
            List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
            return bookRating.stream()
                .mapToDouble(item -> item.getRating())
                .average();
        } catch (Exception e) {
            e.printStackTrace();
            return OptionalDouble.empty();
        }
    }

    public int getRatingCount(long bookID) {
        return bookRepo.findByBookID(bookID).size();
    }

    
}

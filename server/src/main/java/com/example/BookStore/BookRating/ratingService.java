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
    private final BookRatingInfoRepo bookRepo;

    @Autowired
    public ratingService(BookRatingInfoRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void postRating(long bookID, String userID, int rating) {
        List<BookRatingInfo> bookRating = bookRepo.findByBookIDAndUserID(bookID, userID);
        if (!bookRating.isEmpty()) {
            BookRatingInfo ratingInfo = bookRating.get(0);
            ratingInfo.setRating(rating);
            bookRepo.save(ratingInfo); // Added save for update
        } else {
            BookRatingInfo newRatingInfo = new BookRatingInfo();
            newRatingInfo.setBookID(bookID);
            newRatingInfo.setUserID(userID);
            newRatingInfo.setRating(rating);
            bookRepo.save(newRatingInfo);
        }
    }

    public void postComment(long bookID, String userID, String comment) {
        List<BookRatingInfo> bookRating = bookRepo.findByBookIDAndUserID(bookID, userID);
        if (!bookRating.isEmpty()) {
            BookRatingInfo ratingInfo = bookRating.get(0);
            ratingInfo.setComment(comment);
            bookRepo.save(ratingInfo); // Added save for update
        } else {
            BookRatingInfo newRatingInfo = new BookRatingInfo();
            newRatingInfo.setBookID(bookID);
            newRatingInfo.setUserID(userID);
            newRatingInfo.setComment(comment);
            bookRepo.save(newRatingInfo);
        }
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
            // Try using repository method first
            Double avgRating = bookRepo.getAverageRatingForBook(bookID);
            if (avgRating != null) {
                return OptionalDouble.of(avgRating);
            }
            
            // Fall back to manual calculation if needed
            List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
            return bookRating.stream()
                .filter(item -> item.getRating() != null) // Added null check
                .mapToDouble(item -> item.getRating())
                .average();
        } catch (Exception e) {
            e.printStackTrace();
            return OptionalDouble.empty();
        }
    }
    
    public int getRatingCount(long bookID) {
        try {
            return bookRepo.countByBookID(bookID);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
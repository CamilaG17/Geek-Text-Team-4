package com.example.BookStore.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ratingService {
    @Autowired
    private BookRatingInfoRepo bookRepo;
    private Clock clock;
    @Autowired
    public ratingService(Clock clock){
        this.clock = clock;
    }

    public void postRating(long bookID, String userID, int rating){
        LocalDate dateStamp = LocalDate.now(clock);

        BookRatingInfo newRatingInfo = new BookRatingInfo();
        newRatingInfo.setBookID(bookID);
        newRatingInfo.setUserID(userID);
        newRatingInfo.setRating(rating);
        newRatingInfo.setDate(dateStamp);

        bookRepo.save(newRatingInfo);

    }

    public void postComment(long bookID, String userID, String comment){
        LocalDate dateStamp = LocalDate.now(clock);

        BookRatingInfo newRatingInfo = new BookRatingInfo();
        newRatingInfo.setBookID(bookID);
        newRatingInfo.setUserID(userID);
        newRatingInfo.setComment(comment);
        newRatingInfo.setDate(dateStamp);

        bookRepo.save(newRatingInfo);
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

}

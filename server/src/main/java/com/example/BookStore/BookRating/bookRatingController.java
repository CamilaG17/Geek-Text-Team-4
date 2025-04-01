package com.example.BookStore.BookRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BookStore.BookDetails.Book;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api/rating") // Added a base path for all of us to have consistency.
public class bookRatingController {
    @Autowired
    private ratingService ratingService;

    @PostMapping("/postRating/{bookID}/{userID}/{rating}") //User can post rating
    public void postRating(@PathVariable long bookID, String userID, int rating){
        ratingService.postRating(bookID, userID, rating);
    }

    @PostMapping("/postComment/{bookID}/{userID}/{comment}") //User can post comment
    public void postComment(@PathVariable long bookID, String userID, String comment){
        ratingService.postComment(bookID, userID, comment);
    }

    @GetMapping("/getAllComment/{bookID}") //User can get all comments from bookID
    public List<String> getAllComment(@PathVariable long bookID){
        return ratingService.getAllComment(bookID);
    }

    @GetMapping("/getRatingAvg/{bookID}") //User can get all ratings and calcuate avg from bookID
    public OptionalDouble getRatingAvg(@PathVariable long bookID){
        return getRatingAvg(bookID);
    }

}

package com.example.BookStore.BookRating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api/rating") // Added a base path for all of us to have consistency.
public class bookRatingController {
    private final ratingService ratingService;

    public bookRatingController(ratingService ratingService, BookRatingInfoRepo bookRepo) {
        this.ratingService = ratingService;
    }

     @PostMapping("/postRating/{bookID}/{userID}/{rating}")
    public ResponseEntity<String> postRating(
            @PathVariable long bookID, 
            @PathVariable String userID, 
            @PathVariable int rating) {
        ratingService.postRating(bookID, userID, rating);
        return ResponseEntity.ok("Rating posted successfully");
    }

    @PostMapping("/postComment/{bookID}/{userID}/{comment}")
    public ResponseEntity<String> postComment(
            @PathVariable long bookID, 
            @PathVariable String userID,  
            @PathVariable String comment) {
        ratingService.postComment(bookID, userID, comment);
        return ResponseEntity.ok("Comment posted successfully");
    }

    @GetMapping("/getRatingAvg/{bookID}")
public ResponseEntity<?> getRatingAvg(@PathVariable long bookID) {
    try {
        OptionalDouble averageOpt = ratingService.getRatingAvg(bookID);
        int count = ratingService.getRatingCount(bookID);
        
        
        double average = averageOpt.isPresent() ? averageOpt.getAsDouble() : 0.0;
        
     
        Map<String, Object> response = new HashMap<>();
        response.put("average", average);
        response.put("count", count);
        
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        e.printStackTrace();
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Failed to get rating average");
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }
}

@GetMapping("/getAllComment/{bookID}")
public ResponseEntity<?> getAllComment(@PathVariable long bookID) {
    try {
        List<Map<String, Object>> comments = ratingService.getAllCommentWithUsername(bookID);
        return ResponseEntity.ok(comments);
    } catch (Exception e) {
        e.printStackTrace();
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Failed to get comments");
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }
}
}

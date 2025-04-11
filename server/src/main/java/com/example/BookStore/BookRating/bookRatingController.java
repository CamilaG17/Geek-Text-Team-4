package com.example.BookStore.BookRating;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api/rating")
public class bookRatingController {
    private final ratingService ratingService;

    public bookRatingController(ratingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Rating service is running");
    }

    @PostMapping("/postRating/{bookID}/{userID}/{rating}")
    public ResponseEntity<String> postRating(
            @PathVariable String bookID, 
            @PathVariable String userID, 
            @PathVariable int rating) {
        try {
            long bookIdLong = Long.parseLong(bookID);
            ratingService.postRating(bookIdLong, userID, rating);
            return ResponseEntity.ok("Rating posted successfully");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid book ID format");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error posting rating: " + e.getMessage());
        }
    }

    @PostMapping("/postComment/{bookID}/{userID}/{comment}")
    public ResponseEntity<String> postComment(
            @PathVariable String bookID, 
            @PathVariable String userID,  
            @PathVariable String comment) {
        try {
            long bookIdLong = Long.parseLong(bookID);
            ratingService.postComment(bookIdLong, userID, comment);
            return ResponseEntity.ok("Comment posted successfully");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid book ID format");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error posting comment: " + e.getMessage());
        }
    }

    @GetMapping("/getRatingAvg/{bookID}")
    public ResponseEntity<?> getRatingAvg(@PathVariable String bookID) {
        try {
            // Convert ISBN string to long
            long bookIdLong;
            try {
                bookIdLong = Long.parseLong(bookID);
            } catch (NumberFormatException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid ISBN format");
                errorResponse.put("message", "ISBN must be numeric");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Get average rating and count
            OptionalDouble averageOpt = ratingService.getRatingAvg(bookIdLong);
            double average = averageOpt.isPresent() ? averageOpt.getAsDouble() : 0.0;
            int count = ratingService.getRatingCount(bookIdLong);
            
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
    public ResponseEntity<?> getAllComment(@PathVariable String bookID) {
        try {
            long bookIdLong = Long.parseLong(bookID);
            List<Map<String, Object>> comments = ratingService.getAllCommentWithUsername(bookIdLong);
            return ResponseEntity.ok(comments);
        } catch (NumberFormatException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid ISBN format");
            errorResponse.put("message", "ISBN must be numeric");
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to get comments");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
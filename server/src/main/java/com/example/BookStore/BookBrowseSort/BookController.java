package com.example.BookStore.BookBrowseSort;

import org.springframework.web.bind.annotation.*;
import com.example.BookStore.BookDetails.Book;
import java.util.List;
import java.util.Map;

@RestController("browseSortBookController") // Changed the name to avoid conflicts
@RequestMapping("/browse") // Changed the base path to avoid conflicts with BookDetails file
public class BookController {

    private final BrowseSortService browseSortService;

    public BookController(BrowseSortService browseSortService) {
        this.browseSortService = browseSortService;
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return browseSortService.getBooksByGenre(genre);
    }

    @GetMapping("/top-selling")
    public List<Book> getTopSellingBooks() {
        return browseSortService.getTopSellingBooks();
    }

    // Get books by rating
    @GetMapping("/rating/{rating}")
    public List<Map<String, Object>> getBooksByRating(@PathVariable int rating) {
    return browseSortService.getBooksByRating(rating);
}

    // Apply discount by publisher
    @PutMapping("/discount")
    public void applyDiscount(@RequestParam String publisher, @RequestParam double discountPercent) {
        browseSortService.applyDiscount(publisher, discountPercent);
    }
}
//TODO: Implement discount PostMapping for Book Rating Feature
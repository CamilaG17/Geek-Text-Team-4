package com.team4.bookstore_api.controller;

import org.springframework.web.bind.annotation.*;
import com.team4.bookstore_api.service.BookService;
import com.team4.bookstore_api.model.Book;
import java.util.List;


@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }


    public List<Book> getTopSellingBooks() {
        return bookService.getTopSellingBooks();
    }

    // Get books by rating (greater or equal)
    @GetMapping("/rating/{rating}")
    public List<Book> getBooksByRating(@PathVariable double rating) {
        return bookService.getBooksByRating(rating);
    }

    // Apply discount by publisher
    @PutMapping("/discount")
    public void applyDiscount(@RequestParam String publisher, @RequestParam double discountPercent) {
        bookService.applyDiscount(publisher, discountPercent);
    }
}



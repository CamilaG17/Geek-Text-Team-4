package com.example.BookStore.BookBrowseSort;

import com.example.BookStore.BookDetails.Book;
import com.example.BookStore.BookRating.BookRatingInfo;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("browseSortService")
public class BrowseSortService {

    private final BrowseSortRepo browseSortRepo;


    public BrowseSortService(BrowseSortRepo browseSortRepo) {
        this.browseSortRepo = browseSortRepo;
    }

    // Get books by genre
    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = browseSortRepo.findByGenreIgnoreCase(genre);
        System.out.println("Genre searched: " + genre + " — Books found: " + books.size());
        return books;
    }

    // Get top-selling books
    public List<Book> getTopSellingBooks() {
        PageRequest topTen = PageRequest.of(0, 10);
        return browseSortRepo.findTopSellingBooks(topTen).getContent();
    }

    // Get books by rating with rating information
    public List<Map<String, Object>> getBooksByRating(int rating) {
        List<Object[]> results = browseSortRepo.findBooksWithRatingDetails(rating);
        
        return results.stream().map(result -> {
            Map<String, Object> bookDetails = new HashMap<>();
            Book book = (Book) result[0];
            BookRatingInfo ratingInfo = (BookRatingInfo) result[1];
            bookDetails.put("book", book);
            bookDetails.put("rating", ratingInfo.getRating());
            bookDetails.put("comment", ratingInfo.getComment());
            bookDetails.put("username", ratingInfo.getUserID());
            return bookDetails;
        }).collect(Collectors.toList());
    }

    // Apply discount by publisher
    @Transactional
    public void applyDiscount(String publisher, double discountPercent) {
        List<Book> books = browseSortRepo.findAll();

        for (Book book : books) {
            // Updated to use getAuthor() and getPublisher()
            if (book.getAuthor() != null && book.getAuthor().getPublisher().equalsIgnoreCase(publisher)) {
                double oldPrice = book.getprice();
                double newPrice = oldPrice * (1 - discountPercent / 100);
                book.setprice(Math.round(newPrice * 100.0) / 100.0); // round to 2 decimal places
            }
        }

        browseSortRepo.saveAll(books); // persist all updates
        System.out.println(">>> Discount applied to books from publisher: " + publisher);
    }
}
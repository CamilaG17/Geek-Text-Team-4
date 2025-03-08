package com.team4.bookstore_api.service;

import com.team4.bookstore_api.model.Book;
import com.team4.bookstore_api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service // Marks class as a spring service component
public class BookService {

    @Autowired
    private Repository bookRepository;

    public BookService(Repository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //Get by book genre
    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        System.out.println("Books found: " + books.size());  // Log the number of books found
        return books;
    }

    public List<Book> getTopSellingBooks() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }

    // Get books by rating (greater or equal)
    public List<Book> getBooksByRating(double rating) {
        return bookRepository.findByRatingGreaterThanEqual(rating);
    }

    // Apply discount by publisher
    public void applyDiscount(String publisher, double discountPercent) {
        bookRepository.applyDiscount(publisher, discountPercent);
        System.out.println("Discount applied to books from publisher: " + publisher);
    }
}




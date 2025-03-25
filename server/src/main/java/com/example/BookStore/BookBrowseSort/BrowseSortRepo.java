package com.example.BookStore.BookBrowseSort;

import com.example.BookStore.BookDetails.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BrowseSortRepo extends JpaRepository<Book, Long> {
    // Find books by genre
    List<Book> findByGenre(@Param("genre") String genre);

    // Find books with ratings equal to value
    @Query("SELECT b, r FROM Book b, BookRatingInfo r WHERE b.isbn = r.bookID AND r.rating = :rating")
    List<Object[]> findBooksWithRatingDetails(@Param("rating") int rating);
    
    // Get top selling books - using correct method name to match the DESC sorting
    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    Page<Book> findTopSellingBooks(Pageable pageable);

    // Apply discount to books by publisher
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.price = b.price * (1 - :discountPercent / 100) WHERE b.author.publisher = :publisher")
    void applyDiscount(@Param("publisher") String publisher, @Param("discountPercent") double discountPercent);
}
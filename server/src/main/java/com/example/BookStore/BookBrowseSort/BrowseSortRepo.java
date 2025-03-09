package com.example.BookStore.BookBrowseSort;

import com.example.BookStore.BookDetails.Book;
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

    // Find books with ratings greater than or equal value
    @Query("SELECT b, r FROM Book b JOIN BookRatingInfo r ON b.isbn = r.bookID WHERE r.rating = :rating")
    List<Object[]> findBooksWithRatingDetails(@Param("rating") int rating);
    // Get top 10 best-selling books
    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    List<Book> findTop10ByOrderByCopiesSoldDesc();

    // Apply discount to books by publisher
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.price = b.price * (1 - :discountPercent / 100) WHERE b.author.publisher = :publisher")
    void applyDiscount(@Param("publisher") String publisher, @Param("discountPercent") double discountPercent);
}
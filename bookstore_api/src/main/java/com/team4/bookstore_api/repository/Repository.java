package com.team4.bookstore_api.repository;

import com.team4.bookstore_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Interface for access and managing book entites
@org.springframework.stereotype.Repository//A spring data repository
public interface Repository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(@Param("genre") String genre);

    List<Book> findByRatingGreaterThanEqual(@Param("rating") double rating);

    // Get top 10 best-selling books (based on copies sold)
    @Query("SELECT b FROM Book b ORDER BY b.copiesSold DESC")
    List<Book> findTop10ByOrderByCopiesSoldDesc();

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.price = b.price * (1 - :discountPercent / 100) WHERE b.publisher = :publisher")
    void applyDiscount(@Param("publisher") String publisher, @Param("discountPercent") double discountPercent);
}

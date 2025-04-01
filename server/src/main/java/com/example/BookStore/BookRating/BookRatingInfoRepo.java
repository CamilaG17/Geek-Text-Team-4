package com.example.BookStore.BookRating;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BookStore.BookDetails.Book;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    Optional<Book> findByBookID(Long bookID);

    @Query("SELECT AVG(br.rating) FROM BookRatingInfo br WHERE br.bookID = :bookId")
    Double getAverageRatingForBook(@Param("bookId") Long bookId);
}

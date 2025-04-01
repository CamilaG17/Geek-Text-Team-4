package com.example.BookStore.BookRating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    List<BookRatingInfo> findByBookID(Long bookID);

    List<BookRatingInfo> findByBookIDAndUserID(long bookID, String userID);

     @Query("SELECT AVG(r.rating) FROM BookRatingInfo r WHERE r.bookID = :bookId")
    Double getAverageRatingForBook(@Param("bookId") Long bookId);
}

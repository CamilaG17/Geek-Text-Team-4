package com.example.BookStore.BookRating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo, Long> {
    List<BookRatingInfo> findByBookID(Long bookID);
    List<BookRatingInfo> findByBookIDAndUserID(Long bookID, String userID);
    
    @Query("SELECT AVG(r.rating) FROM BookRatingInfo r WHERE r.bookID = :bookId AND r.rating IS NOT NULL")
    Double getAverageRatingForBook(@Param("bookId") Long bookId);
    
    @Query("SELECT COUNT(r) FROM BookRatingInfo r WHERE r.bookID = :bookId")
    int countByBookID(@Param("bookId") Long bookId);
}
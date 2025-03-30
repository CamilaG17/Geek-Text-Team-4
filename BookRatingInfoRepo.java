package com.example.rateAndComment.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    List<BookRatingInfo> findByBookID(long bookID);

    BookRatingInfo findByBookId(Long bookID);

    List<BookRatingInfo> findByBookIDAndUserID(long bookID, String userID);
}

package com.example.rateAndComment.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    BookRatingInfo getBookID(long bookID);

    List<BookRatingInfo> findByBookID(long bookID);

    BookRatingInfo findByBookId(Long bookID);
}

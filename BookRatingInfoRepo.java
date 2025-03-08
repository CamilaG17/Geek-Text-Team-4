package com.example.rateAndComment.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    BookRatingInfo getBookId(long bookID);
}

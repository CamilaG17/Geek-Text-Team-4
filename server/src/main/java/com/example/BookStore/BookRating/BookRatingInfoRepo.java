package com.example.BookStore.BookRating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    BookRatingInfo findByBookID(Long bookID);
}

package com.example.BookStore.BookRating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingInfoRepo extends JpaRepository<BookRatingInfo,Long> {

    List<BookRatingInfo> findByBookID(Long bookID);

    List<BookRatingInfo> findByBookIDAndUserID(long bookID, String userID);

}

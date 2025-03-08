package com.example.rateAndComment.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ratingService {
    @Autowired
    private BookRatingInfoRepo bookRepo;

    public List<BookRatingInfo> getAllBooks(){
        return bookRepo.findAll();
    }
    public BookRatingInfo saveBook(BookRatingInfo book){
        return bookRepo.save(book);
    }
    public BookRatingInfo getBookId(long bookID){
        Optional<BookRatingInfo> bookRatingInfo = bookRepo.findById(bookID);
        return bookRatingInfo.orElse(null);
    }
}

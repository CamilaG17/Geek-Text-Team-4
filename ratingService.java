package com.example.rateAndComment.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ratingService {
    @Autowired
    private BookRatingInfoRepo bookRepo;

    public List<BookRatingInfo> getAllBooks(){
        return(List<BookRatingInfo>) bookRepo.findAll();
    }
    public BookRatingInfo saveBook(BookRatingInfo book){
        return bookRepo.save(book);
    }
}

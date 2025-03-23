package com.example.rateAndComment.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

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
        return bookRepo.findByBookId(bookID);
    }

    public List<String> getAllComment(long bookID){
        List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
        return bookRating.stream()
                .map(BookRatingInfo :: getComment)
                .toList();
    }

    public OptionalDouble getRatingAvg(long bookID){
        List<BookRatingInfo> bookRating = bookRepo.findByBookID(bookID);
        return bookRating.stream()
                .mapToDouble(item -> item.getRating())
                .average();
    }
}

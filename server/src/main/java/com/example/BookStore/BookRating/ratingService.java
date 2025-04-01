package com.example.BookStore.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStore.BookDetails.Book;

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
    public Optional<Book> getBookId(long bookID){
        return bookRepo.findByBookID(bookID);
    }
}

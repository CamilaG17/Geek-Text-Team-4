package com.example.rateAndComment.bookstore.controllers;

import com.example.rateAndComment.bookstore.BookRatingInfo;
import com.example.rateAndComment.bookstore.BookRatingInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class bookRatingController {
    @Autowired
    private BookRatingInfoRepo bookRepo;

    @PostMapping("/addBook")
    public ResponseEntity createBook(@RequestBody BookRatingInfo book){
        BookRatingInfo response = bookRepo.save(book);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addAll")
    public List<BookRatingInfo> addList(@RequestBody List<BookRatingInfo> bookList){
        return bookRepo.saveAll(bookList);
    }

    @GetMapping("/getAll")
    public List<BookRatingInfo> getAllBook(){
        return bookRepo.findAll();
    }

    @GetMapping("/getBook/{id}")
    public BookRatingInfo getBookId(@PathVariable long bookID){
        return bookRepo.getBookId(bookID);
    }

}

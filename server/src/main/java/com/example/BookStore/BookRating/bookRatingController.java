package com.example.BookStore.BookRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Added a base path for all of us to have consistency.
public class bookRatingController {
    @Autowired
    private BookRatingInfoRepo bookRepo;

    @PostMapping("/addBook")
    public ResponseEntity<BookRatingInfo> createBook(@RequestBody BookRatingInfo book){
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
    public BookRatingInfo getBookId(@PathVariable long id){
        return bookRepo.findByBookID(id);
    }

}

package com.example.BookStore.BookRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BookStore.BookDetails.Book;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api/rating") // Added a base path for all of us to have consistency.
public class bookRatingController {
    @Autowired
    private BookRatingInfoRepo bookRepo;
    private ratingService ratingService;

   /* @PostMapping("/addBook")
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
    public BookRatingInfo getBookId(@PathVariable long bookID){
        return bookRepo.findByBookId(bookID);
    }
    */
    //--------------------------------------------------------------------------------

    @GetMapping("/getAllComment/{id}")
    public List<String> getAllComment(@PathVariable long bookID){
        return ratingService.getAllComment(bookID);
    }

    @GetMapping("/getRatingAvg/{id}")
    public OptionalDouble getRatingAvg(@PathVariable long bookID){
        return getRatingAvg(bookID);
    }

}

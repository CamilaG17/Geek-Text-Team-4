package com.example.BookStore;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") 
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }
    @GetMapping("/books/by-author/{lastName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String lastName) {
    List<Book> books = bookService.getBooksByAuthor(lastName);
    if (!books.isEmpty()) {
        return ResponseEntity.ok(books);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }
    }
}

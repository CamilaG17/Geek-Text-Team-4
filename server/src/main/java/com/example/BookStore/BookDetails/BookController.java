package com.example.BookStore.BookDetails;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  // this is the base URL for all the endpoints in this class
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired


    @GetMapping("/books/All")
    public List<Book> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    
    for (Book book : books) {
        System.out.println("Book: " + book.getBookName());
        if (book.getAuthor() != null) {
            System.out.println("Author ID: " + book.getAuthor().getId());
            System.out.println("Author Name: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
        } else {
            System.out.println("No author associated with book");
        }
    }
    
    return books;
}
    
    @GetMapping("/books/firstName/{firstName}")
    public List<Book> getBooksByAuthorFirstName(@PathVariable String firstName){
    return bookService.getBooksByAuthorFirstName(firstName);
}
    @GetMapping("/books/lastName/{lastName}")
    public List<Book> getBooksByAuthorLastName(@PathVariable String lastName){
        return bookService.getBooksByAuthorLastName(lastName);
    }

    @GetMapping("/books/id/{id}")//Get book by ISBN
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{bookName}")//Get book by title
    public Book getByBookName(@PathVariable String bookName) {
        return bookService.getByBookName(bookName);
    }
    @GetMapping("/authors/All")//Get all authors
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/id/{id}")//Get author by author ID
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/create/book")//Create a new book
    public ResponseEntity<?> createBook(@RequestBody Book book) {
    try {
        Book savedBook = bookService.saveBook(book);
        System.out.println("Successfully created book: " + savedBook.getBookName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    } catch (Exception e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}
    @PostMapping("/create/author")//Create a new author
    public ResponseEntity<?> createAuthor(@RequestBody Author author){
        try{
            Author savedAuthor = authorService.saveAuthor(author);
            System.out.println("Successfully created author: " + savedAuthor.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}


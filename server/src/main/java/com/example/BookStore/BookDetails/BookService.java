package com.example.BookStore.BookDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private Bookrepository bookRepository;

    public List<Book> getAllBooks() {
        // Use a method that ensures authors are loaded
        List<Book> books = bookRepository.findAll();
        
        // Force initialization of lazy-loaded authors
        books.forEach(book -> {
            if (book.getAuthor() != null) {
                book.getAuthor().getFirstName(); // This can help trigger lazy loading
            }
        });
        
        return books;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElse(null);
    }

    public Book getByBookName(String bookName) {
        return bookRepository.findByBookName(bookName)
            .orElse(null);
    }

    public List<Book> getBooksByAuthorFirstName(String firstName) {
        return bookRepository.findByAuthorFirstName(firstName);
    }

    public List<Book> getBooksByAuthorLastName(String lastName) {
        return bookRepository.findByAuthorLastName(lastName);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
}
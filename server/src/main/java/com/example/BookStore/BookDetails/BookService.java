package com.example.BookStore.BookDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private Bookrepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    public Book getByBookName(String bookName){
        Optional<Book> book =bookRepository.findByBookName(bookName);
        return book.orElse(null);
    }
    public List<Book> getBooksByAuthorFirstName(String firstName){
        return bookRepository.findByAuthorFirstName(firstName);
    }
    public List<Book> getBooksByAuthorLastName(String lastName){
        return bookRepository.findByAuthorLastName(lastName);
    }
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
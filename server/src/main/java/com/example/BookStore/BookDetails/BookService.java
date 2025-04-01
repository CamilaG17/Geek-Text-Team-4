package com.example.BookStore.BookDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStore.BookRating.BookRatingInfoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private Bookrepository bookRepository;
    
    @Autowired
    private BookRatingInfoRepo bookRatingInfoRepo;

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            Double averageRating = bookRatingInfoRepo.getAverageRatingForBook(book.getISBN());
            book.setAverageRating(averageRating != null ? averageRating : 0.0);
        }
        
        return books;
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
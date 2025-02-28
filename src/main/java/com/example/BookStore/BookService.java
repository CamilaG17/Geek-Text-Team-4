package com.example.BookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

@Autowired
private Bookrepository BookRepository;
@Autowired
private Authorrepository AuthorRepository;

public List<Book> getAllBooks(){
    return(List<Book>) BookRepository.findAll();
}
public Book getBookById(Long isbn){
    return BookRepository.findById(isbn).orElse(null);
}
public Book saveBook(Book book){
    return BookRepository.save(book);
}
public List<Book> getBooksByAuthor(String lastName) {
    return BookRepository.findByAuthor_LastName(lastName);
}

}

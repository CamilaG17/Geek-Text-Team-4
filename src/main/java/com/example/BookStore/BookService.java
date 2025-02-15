package com.example.BookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

@Autowired
private Bookrepository BookRepository;

public List<Book> getAllBooks(){
    return(List<Book>) BookRepository.findAll();
}
public Book getBookById(Long isbn){
    return BookRepository.findById(isbn).orElse(null);
}
public Book saveBook(Book book){
    return BookRepository.save(book);
}
}

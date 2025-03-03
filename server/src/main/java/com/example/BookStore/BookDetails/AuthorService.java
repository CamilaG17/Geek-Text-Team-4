package com.example.BookStore.BookDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

@Autowired
private Authorrepository AuthorRepository;

public List<Author> getAllAuthors(){
    return(List<Author>) AuthorRepository.findAll();
}
public Author getAuthorById(Long id){
    return AuthorRepository.findById(id).orElse(null);
}
public Author saveAuthor(Author author){
    return AuthorRepository.save(author);
}
}


package com.example.BookStore.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface Bookrepository extends JpaRepository<Book, Long>{
    Optional<Book> findByBookName(String bookName);
    List<Book> findByAuthorFirstName(String firstName);
    List<Book> findByAuthorLastName(String lastName);
    
}

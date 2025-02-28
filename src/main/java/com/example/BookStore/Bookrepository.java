package com.example.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface Bookrepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_LastName(String lastName);
}

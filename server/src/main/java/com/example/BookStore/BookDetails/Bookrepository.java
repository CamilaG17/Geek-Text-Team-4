package com.example.BookStore.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bookrepository extends JpaRepository<Book, Long>{
 

}

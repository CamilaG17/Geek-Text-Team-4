package com.example.BookStore.BookDetails;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="author")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {
    @Id
    @Column(name="authorid")
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;  

    @Column(name="biography")
    private String biography;

    @Column(name="publisher")
    private String publisher;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> books;

    // Getters and setters with consistent naming
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {  // Changed return type to Long
        return id;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {  // Corrected method name
        this.lastName = lastName;
    }

    public String getLastName() {  // Corrected method name
        return lastName;
    }

    public void setBiography(String biography) {  
        this.biography = biography;
    }

    public String getBiography() {  
        return biography;
    }

    public void setPublisher(String publisher) {  
        this.publisher = publisher;
    }

    public String getPublisher() {  
        return publisher;
    }

    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
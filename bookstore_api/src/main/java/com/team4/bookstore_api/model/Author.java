package com.team4.bookstore_api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public void setId(Long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setlastName(String lastName){
        this.lastName=lastName;
    }
    public String getlastName(){
        return lastName;
    }
    public void setbiography(String biography){
        this.biography=biography;
    }
    public String getbiography(){
        return biography;
    }
    public void setpublisher(String publisher){
        this.publisher=publisher;
    }
    public String getpublisher(){
        return publisher;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
package com.example.BookStore.BookDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    
    @Id
    private Long isbn;

    @Column(name="bookname")
    private String bookName;
    @Column(name="bookdescription")
    private String bookDescription;
    @Column(name="price")
    private double price;
    @Column(name="genre")
    private String genre;
    @Column(name="yearpublished")
    private int year;
    @Column(name="copiessold")
    private Long copiesSold;

    @Transient 
    private Double averageRating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="authorid")
    private Author author;


    public Book(){}

    public Book(Long isbn, String bookName, String bookDescription, double price, Author author, String genre, int year, Long copiesSold) {         
        this.bookName = bookName;         
        this.isbn = isbn;         
        this.bookDescription = bookDescription;         
        this.price = price;
        this.author = author; // Added this line
        this.genre = genre;         
        this.year = year;         
        this.copiesSold = copiesSold;     
    }  
   // Getters and Setters
    public Author getAuthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author=author;
    }
    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    public String getBookName(){
        return bookName;
    }
    public void setISBN(Long isbn){
        this.isbn = isbn;
    }
    public Long getISBN(){
        return this.isbn;
    }
    public void setbookDescription(String bookDescription){
        this.bookDescription= bookDescription;
    }
    public String getbookDescription(){
        return this.bookDescription;
    }
    public void setprice(double price){
        this.price=price;
    }
    public double getprice(){
        return this.price;
    }
    public void setgenre(String genre){
        this.genre=genre;
    }
    public String getgenre(){
        return this.genre;
    }
    public void setyear(int year){
        this.year=year;
    }
    public int getyear(){
        return this.year;
    }
    public void setcopiesSold(Long copiesSold){
        this.copiesSold=copiesSold;
    }
    public Long getcopiesSold(){
        return this.copiesSold;
    }
    public Double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

   
}

package com.example.BookStore.BookDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToOne
    @JoinColumn(name="authorid")
    private Author author;


    public Book(){}

    public Book(Long isbn, String bookName,String bookDescription, double price, String author, String genre, int year, Long copiesSold){
        this.bookName= bookName;
        this.isbn =isbn;
        this.bookDescription = bookDescription;
        this.price=price;
        this.genre=genre;
        this.year=year;
        this.copiesSold=copiesSold;
    }

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
    public void setBookDescription(String bookDescription){
        this.bookDescription= bookDescription;
    }
    public String getBookDescription(){
        return this.bookDescription;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public double getPrice(){
        return this.price;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public String getGenre(){
        return this.genre;
    }
    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return this.year;
    }
    public void setCopiesSold(Long copiesSold){
        this.copiesSold=copiesSold;
    }
    public Long getCopiesSold(){
        return this.copiesSold;
    }
}

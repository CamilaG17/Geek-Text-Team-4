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

    public Author getauthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author=author;
    }
    public void setbookName(String bookName){
        this.bookName = bookName;
    }
    public String getbookName(){
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
}

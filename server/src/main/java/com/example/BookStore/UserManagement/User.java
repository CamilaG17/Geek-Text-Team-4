package com.example.BookStore.UserManagement;

import jakarta.persistence.*;

@Entity
@Table(name = "users") //Database table is named users
public class User {
    
    @Id
    @Column(nullable = false)//Username field cannot be empty and there cannot be duplicates
    private String username;
    
    @Column(nullable = false) //Required attriutes for user and cannot be empty
    private String password;
    private String name;
    private String email;
    private String homeaddress;
    private String creditcard;
    
    public User() {
    } 

    public User(String username, String password, String name, String email, String homeaddress, String creditcard) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.homeaddress = homeaddress;
        this.creditcard = creditcard;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getHomeaddress() {
        return homeaddress;
    }
    
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }
    
    public String getCreditcard() {
        return creditcard;
    }
    
    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }
}
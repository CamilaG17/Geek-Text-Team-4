package com.example.BookStore.UserManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each credit card entry

    private String creditCardNumber; // The credit card number

    // Default constructor
    public CreditCard() {}

    // Constructor
    public CreditCard(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    // Getter for the credit card number
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    // Setter for the credit card number
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    // Additional methods like equals, hashcode, etc. can be added if needed
}

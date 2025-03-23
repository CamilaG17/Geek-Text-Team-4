package com.example.BookStore.UserManagement;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(String username) {
        return userRepository.findById(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(String username, User user) {
        if (userRepository.existsById(username)) {
            user.setUsername(username); // Ensure username is not changed
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            return true;
        }
        return false;
    }

    // Add a credit card to a user
    public boolean addCreditCardToUser(String username, CreditCard creditCard) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCreditcard(creditCard.getCreditCardNumber()); // Set the credit card for the user
            userRepository.save(user); // Save the user with the new credit card
            return true;
        }
        return false;
    }
}

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

    // Username is the primary key for the user table
    public Optional<User> findUserById(String username) {
        return userRepository.findById(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
// TODO Add more methods here for user management such as updating user information, deleting a user, etc.
}
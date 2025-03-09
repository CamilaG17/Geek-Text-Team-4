package com.example.BookStore.UserManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
}
//TODO: Add more methods here for user management such as updating user information, deleting a user, etc.
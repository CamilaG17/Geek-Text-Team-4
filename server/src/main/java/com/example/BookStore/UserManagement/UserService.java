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

    public boolean updateUser(String username, User userUpdates) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (userUpdates.getName() != null) {
                existingUser.setName(userUpdates.getName());
            }
            if (userUpdates.getEmail() != null) {
                existingUser.setEmail(userUpdates.getEmail());
            }
            if (userUpdates.getHomeaddress() != null) {
                existingUser.setHomeaddress(userUpdates.getHomeaddress());
            }

            if (userUpdates.getPassword() != null) {
                existingUser.setPassword(userUpdates.getPassword());
            }

            if (userUpdates.getCreditcard() != null) {
                existingUser.setCreditcard(userUpdates.getCreditcard());
            }

            userRepository.save(existingUser);
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


    public boolean addCreditCardToUser(String username, CreditCard creditCard) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCreditcard(creditCard.getCreditCardNumber());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}

package com.example.BookStore.Shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStore.BookDetails.Book;
import com.example.BookStore.BookDetails.Bookrepository;
import com.example.BookStore.UserManagement.User;
import com.example.BookStore.UserManagement.UserRepository;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Bookrepository bookRepository;

    // Get subtotal of the shopping cart
    public double calculateCartSubtotal(String username) {
        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUserUsername(username);
        return cartItems.stream()
                        .mapToDouble(item -> item.getBook().getprice() * item.getQuantity())
                        .sum();
    }

    // Get books in the shopping cart
    public List<Book> getBooksInCart(String username) {
        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUserUsername(username);
        return cartItems.stream()
                        .map(ShoppingCartItem::getBook)
                        .toList();
    }

    public void addToCart(String userId, String isbn){
        System.out.println("🔍 addToCart called with userId = " + userId + ", bookId = " + isbn);

        User user = userRepository.findById(userId)
                  .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findByBookName(isbn)
                  .orElseThrow(() -> new RuntimeException("Book not found"));
    
        ShoppingCartItem item = new ShoppingCartItem();
        item.setUser(user);
        item.setBook(book);
        item.setQuantity(1); // Default to 1 for now
    
        shoppingCartRepository.save(item);
    }

}

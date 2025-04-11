/* package com.example.BookStore.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    public Wishlist saveDetails(Wishlist wishlist) {

        return wishlistRepo.save(wishlist);

    }

    public List<Wishlist> getAllDetails() {
        return wishlistRepo.findAll();
    }

}
 */

package com.example.BookStore.Wishlist;

import com.example.BookStore.BookDetails.Book;
import com.example.BookStore.BookDetails.Bookrepository;
import com.example.BookStore.Shoppingcart.ShoppingCartService;
import com.example.BookStore.UserManagement.User;
import com.example.BookStore.UserManagement.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private WishlistBookRepo wishlistBookRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Bookrepository bookRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    // Create a new wishlist
    public Wishlist createWishlist(String name, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Wishlist wishlist = new Wishlist();
        wishlist.setName(name);   // Set the name of the wishlist
        wishlist.setUser(user);   // Associate it with the user
        return wishlistRepo.save(wishlist);  // Save and return the created wishlist
    }

    // Add a book to a wishlist
    public void addBookToWishlist(Long wishlistId, Long isbn) {
        Wishlist wishlist = wishlistRepo.findById(wishlistId).orElseThrow();
        Book book = bookRepository.findByIsbn(isbn).orElseThrow();

        WishlistBook wb = new WishlistBook();
        wb.setWishlist(wishlist); // Set the wishlist this book belongs to
        wb.setBook(book);         // Set the book being added
        wishlistBookRepo.save(wb); // Save the relation to the database
    }

    // Remove a book from the wishlist and add it to the shopping cart
    public void removeBookAndAddToCart(Long wishlistId, Long isbn, String username) {
        wishlistBookRepo.deleteByWishlistIdAndBookIsbn(wishlistId, isbn); // Remove from wishlist
        shoppingCartService.addToCart(username, isbn);  // Add the book to the shopping cart
    }

    // Get all books in a wishlist
    public List<Book> getBooksInWishlist(Long wishlistId) {
        return wishlistBookRepo.findByWishlistId(wishlistId)
                .stream()
                .map(WishlistBook::getBook)
                .collect(Collectors.toList());
    }
}


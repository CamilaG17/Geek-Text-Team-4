/*package com.example.BookStore.Wishlist;


import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {



}
*/

package com.example.BookStore.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserUsername(String username);
}

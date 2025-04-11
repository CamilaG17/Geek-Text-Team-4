/*package com.example.BookStore.Wishlist;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @Column(name = "wishlistid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistid;
    private Long isbn;

    @Column(name = "username")
    private String username;
}

*/
package com.example.BookStore.Wishlist;

import com.example.BookStore.UserManagement.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data  // This generates the getter/setter methods automatically
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Wishlist ID

    private String name;  // Wishlist name

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;  // Associated user for the wishlist

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private List<WishlistBook> books;  // Books in this wishlist

    public void setName(String name) {
        this.name = name;

    }

    public void setUser(User user) {
        this.user = user;
    }


}

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private List<WishlistBook> books;
}

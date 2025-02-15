package com.example.Shoppingcart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @GetMapping("/{id}")
    public String getCartById(@PathVariable Long id) {
        return "Shopping cart for user: " + id;
    }
}

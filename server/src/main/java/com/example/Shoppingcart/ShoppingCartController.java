package com.example.Shoppingcart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from your React app
public class ShoppingCartController {

    @GetMapping
    public String getTest() {
        return "The test is working!";
    }
}

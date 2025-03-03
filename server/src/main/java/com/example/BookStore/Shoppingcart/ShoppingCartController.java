package com.example.BookStore.Shoppingcart;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from your React app
public class ShoppingCartController {

    @GetMapping
    public Map<String, String> getTest() {
        return Collections.singletonMap("message", "The test is working!");
    }
}

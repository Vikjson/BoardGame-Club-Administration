package se.yrgo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple home controller for testing API availability.
 * <p>
 * Provides a basic endpoint to verify that the application is running
 * and accessible from the client.
 */
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {
    @GetMapping
    public String testHome(){
        System.out.println("Reached HomeController!");
        return "Welcome!";
    }
}

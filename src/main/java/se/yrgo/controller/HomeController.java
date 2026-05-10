package se.yrgo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {
    @GetMapping
    public String testHome(){
        System.out.println("Reached HomeController!");
        return "Testing HomeController";
    }
}

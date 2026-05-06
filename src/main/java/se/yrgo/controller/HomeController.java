package se.yrgo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public String testHome(){
        System.out.println("Reached HomeController!");
        return "Testing HomeController";
    }
}

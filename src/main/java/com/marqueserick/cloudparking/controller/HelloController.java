package com.marqueserick.cloudparking.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "Olá Mundo!";
    }

}

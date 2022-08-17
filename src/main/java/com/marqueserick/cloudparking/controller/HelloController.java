package com.marqueserick.cloudparking.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "Olá Mundo!";
    }

}

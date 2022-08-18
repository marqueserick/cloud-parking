package com.marqueserick.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Hello World")
@RequestMapping("/")
public class HelloController {

    @GetMapping
    @ApiOperation("Saying hello to the world")
    public String hello() {
        return "Olá Mundo!";
    }

}

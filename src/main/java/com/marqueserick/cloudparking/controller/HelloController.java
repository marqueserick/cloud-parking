package com.marqueserick.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "Hello World")
@RequestMapping("/")
@ApiIgnore
public class HelloController {

    @GetMapping
    public String hello() {
        return "Olá Mundo!";
    }

}

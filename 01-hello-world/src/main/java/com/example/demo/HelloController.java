package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("myself")
    public String myself() {
        return "<h1>Myself Amber!</h1>";
    }
}

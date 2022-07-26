package com.example.springbootsystemdsample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class HelloController {
    @GetMapping(path = "")
    public String hello() {
        return "{\"message\": \"hello\"}";
    }
}

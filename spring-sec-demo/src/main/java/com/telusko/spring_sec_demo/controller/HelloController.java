package com.telusko.spring_sec_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String Home() {
        return "nothing to show";
    }

    @GetMapping("/hello")
    public String Greet(HttpServletRequest request) {
        return "hello world || SessionID = " + request.getSession().getId();
    }
}

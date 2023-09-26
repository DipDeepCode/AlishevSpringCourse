package ru.ddc.springcourse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Value("${hello}")
    private String helloMessage;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(helloMessage);
        return "hello";
    }
}

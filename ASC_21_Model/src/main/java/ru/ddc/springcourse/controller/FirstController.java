package ru.ddc.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        System.out.println("Hello " + firstname + " " + lastname);
        return "first/hello";
    }

    @GetMapping("/hello2")
    public String helloPage2(@RequestParam(value = "firstname", required = false) String firstname,
                             @RequestParam("lastname") String lastname,
                             Model model) {
        model.addAttribute("message", "Hello " + firstname + " " + lastname);
        return "first/hello";
    }


    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }
}

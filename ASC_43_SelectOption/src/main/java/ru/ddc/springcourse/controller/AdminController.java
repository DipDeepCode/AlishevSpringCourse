package ru.ddc.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ddc.springcourse.dao.PersonDao;
import ru.ddc.springcourse.model.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDao personDao;

    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String adminPage(Model model,
                            @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDao.index());
        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }
}

package ru.ddc.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ddc.springcourse.dao.PersonDao;
import ru.ddc.springcourse.model.Person;
import ru.ddc.springcourse.service.PersonService;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final PersonDao personDao;

    public PeopleController(PersonService personService,
                            PersonDao personDao) {
        this.personService = personService;
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
//        model.addAttribute("people", personService.findAll());
        personDao.testNPlusOne();
        personDao.testNPlusOneSolution();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "people/new";
        } else {
            personService.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "people/edit";
        } else {
            personService.update(id, person);
            return "redirect:/people";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}

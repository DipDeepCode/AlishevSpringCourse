package ru.ddc.springcourse.FirstRestApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ddc.springcourse.FirstRestApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ddc.springcourse.FirstRestApp.services.PeopleService;
import ru.ddc.springcourse.FirstRestApp.utils.PersonErrorResponse;
import ru.ddc.springcourse.FirstRestApp.utils.PersonNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

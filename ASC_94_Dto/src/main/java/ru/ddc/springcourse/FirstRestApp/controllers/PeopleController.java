package ru.ddc.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.ddc.springcourse.FirstRestApp.dto.PersonDto;
import ru.ddc.springcourse.FirstRestApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ddc.springcourse.FirstRestApp.services.PeopleService;
import ru.ddc.springcourse.FirstRestApp.utils.PersonErrorResponse;
import ru.ddc.springcourse.FirstRestApp.utils.PersonNotCreatedException;
import ru.ddc.springcourse.FirstRestApp.utils.PersonNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper mapper;

    @Autowired
    public PeopleController(PeopleService peopleService,
                            ModelMapper mapper) {
        this.peopleService = peopleService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<PersonDto> getPeople() {
        List<Person> people = peopleService.findAll();
        return people.stream()
                .map((element) -> mapper.map(element, PersonDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") int id) {
        return mapper.map(peopleService.findOne(id), PersonDto.class);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDto personDto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(
                    fieldError -> errorMessage.append(fieldError.getField())
                            .append(" - ")
                            .append(fieldError.getDefaultMessage())
                            .append("; ")
            );
            throw new PersonNotCreatedException(errorMessage.toString());
        }
        peopleService.save(mapper.map(personDto, Person.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

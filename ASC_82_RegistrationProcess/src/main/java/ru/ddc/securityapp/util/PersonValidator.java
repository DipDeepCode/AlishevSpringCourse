package ru.ddc.securityapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ddc.securityapp.model.Person;
import ru.ddc.securityapp.service.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String username = ((Person) o).getUsername();
        Optional<Person> optionalPerson = peopleService.getOptionalPersonByName(username);
        if (optionalPerson.isPresent()) {
            errors.rejectValue("username", "", "Имя пользователя уже занято");
        }
    }
}

package ru.ddc.springcourse.utils;

import org.springframework.stereotype.Component;
import ru.ddc.springcourse.dao.PersonDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {
    private final PersonDao personDao;

    public UniqueEmailValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        return personDao.show(email).isEmpty();
    }
}

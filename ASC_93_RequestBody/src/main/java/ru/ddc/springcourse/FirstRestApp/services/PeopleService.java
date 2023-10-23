package ru.ddc.springcourse.FirstRestApp.services;

import ru.ddc.springcourse.FirstRestApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.springcourse.FirstRestApp.repositories.PeopleRepository;
import ru.ddc.springcourse.FirstRestApp.utils.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }
}
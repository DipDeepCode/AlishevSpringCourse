package ru.ddc.securityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ddc.securityapp.model.Person;
import ru.ddc.securityapp.repository.PeopleRepository;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> getOptionalPersonByName(String username) {
        return peopleRepository.findByUsername(username);
    }
}

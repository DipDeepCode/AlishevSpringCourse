package ru.ddc.securityapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ddc.securityapp.model.Person;
import ru.ddc.securityapp.repository.PeopleRepository;
import ru.ddc.securityapp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = peopleRepository.findByUsername(username);

        if (optionalPerson.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(optionalPerson.get());
    }
}

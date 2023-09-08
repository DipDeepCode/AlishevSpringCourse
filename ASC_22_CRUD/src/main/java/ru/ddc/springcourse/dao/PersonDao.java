package ru.ddc.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.ddc.springcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private final List<Person> people = new ArrayList<>();

    {
        people.add(new Person(1, "Tom"));
        people.add(new Person(2, "Bob"));
        people.add(new Person(3, "Mike"));
        people.add(new Person(4, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}

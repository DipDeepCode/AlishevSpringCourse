package ru.ddc.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ddc.springcourse.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAgeAsc(String name);

    List<Person> findByEmail(String email);

    List<Person> findByNameStartsWith(String name);

    List<Person> findByNameOrEmail(String name, String email);


}

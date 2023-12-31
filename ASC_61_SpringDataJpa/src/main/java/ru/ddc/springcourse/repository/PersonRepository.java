package ru.ddc.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ddc.springcourse.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}

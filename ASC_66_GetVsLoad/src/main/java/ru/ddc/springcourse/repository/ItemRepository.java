package ru.ddc.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.springcourse.model.Item;
import ru.ddc.springcourse.model.Person;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);

    List<Item> findByPerson(Person person);

}

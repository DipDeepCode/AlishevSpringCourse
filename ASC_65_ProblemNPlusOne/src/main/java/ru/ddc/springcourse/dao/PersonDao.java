package ru.ddc.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.internal.AbstractSessionImpl;
import org.springframework.stereotype.Component;
import ru.ddc.springcourse.model.Person;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDao {
    private final EntityManager entityManager;

    public PersonDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void testNPlusOne() {
        try (Session session = entityManager.unwrap(AbstractSessionImpl.class)) {
            List<Person> people = session.createQuery("select p from Person p",
                    Person.class).getResultList();

            for (Person person : people) {
                System.out.println(person.getItems());
            }
        }
    }

    public void testNPlusOneSolution() {
        try (Session session = entityManager.unwrap(AbstractSessionImpl.class)) {
            List<Person> people = session.createQuery("select p from Person p left join fetch p.items",
                    Person.class).getResultList();

            for (Person person : people) {
                System.out.println(person.getItems());
            }
        }
    }
}

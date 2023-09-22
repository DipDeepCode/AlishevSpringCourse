package ru.ddc.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.internal.AbstractSessionImpl;
import org.springframework.stereotype.Component;
import ru.ddc.springcourse.model.Item;
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

    public void getVsLoad() {
        try (Session session = entityManager.unwrap(AbstractSessionImpl.class)) {
            session.get(Person.class, 1L);// Делает запрос к БД и возвращает Person
            session.load(Person.class, 1L);// Не делает запрос к БД, возвращает proxy объект
            // если объект ранее не загружался, то все поля кроме id будут равны null
            // но если выполнить get на любое поле, то будет сделан запрос к БД

            // Можно использовать этот метод для выстраивания связи
            // Создаем новый товар
            Item item = new Item();
            // Получаем пользователя
            Person personProxy = session.load(Person.class, 1L);
            // Назначаем товару владельца
            item.setPerson(personProxy);

        }
    }
}


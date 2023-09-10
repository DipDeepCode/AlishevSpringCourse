package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Item;
import ru.ddc.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Test cascading", 30);
            person.addItem(new Item("Test cascading item1"));
            person.addItem(new Item("Test cascading item2"));
            person.addItem(new Item("Test cascading item3"));

//            session.persist(person); // сохранение с каскадированием для JPA
            session.save(person);


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}

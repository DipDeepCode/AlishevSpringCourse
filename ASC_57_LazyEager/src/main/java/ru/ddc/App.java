package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Item;
import ru.ddc.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // lazy
//            Person person = session.get(Person.class, 1);
//            System.out.println(person);
//            System.out.println(person.getItems());

            // eager
            Item item = session.get(Item.class, 1);
            System.out.println(item);
            System.out.println(item.getPerson());

            session.getTransaction().commit();

        }
    }
}

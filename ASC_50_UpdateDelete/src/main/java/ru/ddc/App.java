package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Update
//            Person person = session.get(Person.class, 2);
//            person.setName("new name");

            // Delete
//            Person person = session.get(Person.class, 2);
//            session.delete(person);

            // Get id after save
            Person person = new Person("some name", 30);
            session.save(person);

            session.getTransaction().commit();

            // Get id after save
            System.out.println(person.getId());

        } finally {
            sessionFactory.close();
        }
    }
}

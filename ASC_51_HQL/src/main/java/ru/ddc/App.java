package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Person;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            List<Person> people = session.createQuery("from Person").getResultList();

//            List<Person> people = session.createQuery("from Person where age > 30").getResultList();

//            List<Person> people = session.createQuery("from Person where name like 'T%'").getResultList();
//            for (Person person : people) {
//                System.out.println(person);
//            }

//            session.createQuery("update Person set name='Test' where age < 31").executeUpdate();

            session.createQuery("delete from Person where age < 31").executeUpdate();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}

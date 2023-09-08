package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Item;
import ru.ddc.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Получение человека и его товаров
//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items );

            // Получение товара и его человека
//            Item item = session.get(Item.class, 1);
//            System.out.println(item);
//            Person person = item.getPerson();
//            System.out.println(person);

            // Добавление нового товара
//            Person person = session.get(Person.class, 2);
//            Item item = new Item("item_f_h", person);
//            person.getItems().add(item);
//            session.save(item);

            // Добавление человека с товаром
//            Person person = new Person("Test person", 30);
//            Item item = new Item("Item_f_h_2", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));
//            session.save(person);
//            session.save(item);

            // Удалить товар у человека
//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//            for (Item item : items) {
//                session.remove(item);
//            }
//            items.clear();

            // Удалить человека
//            Person person = session.get(Person.class, 2);
//            session.remove(person);
//            person.getItems().forEach(item -> item.setPerson(null));

            // Поменять владельца у существующего товара
            Item item = session.get(Item.class, 1);
            item.getPerson().getItems().remove(item);
            Person person = session.get(Person.class, 4);
            item.setPerson(person);
            person.getItems().add(item);


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}

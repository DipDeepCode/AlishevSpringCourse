package ru.ddc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddc.model.Actor;
import ru.ddc.model.Movie;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("HK", 81);
//            Actor actor2 = new Actor("SJ", 72);
//
//            movie.addActor(actor1);
//            movie.addActor(actor2);
//
//            actor1.addMovie(movie);
//            actor2.addMovie(movie);
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie);
//            System.out.println(movie.getActors());

//            Movie movie = new Movie("Reservoir dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//            movie.addActor(actor);
//            actor.addMovie(movie);
//            session.save(movie);

            Actor actor = session.get(Actor.class, 2);
            Movie movieToRemove = actor.getMovies().get(0);
            actor.getMovies().remove(movieToRemove);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();

        }
    }
}

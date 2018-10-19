package es.udc.lbd.asi.restexample.model.repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.domain.User;

public class InMemoryDB {
    public static Map<Long, User> users = new HashMap<Long, User>();
    public static Map<Long, Post> posts = new HashMap<Long, Post>();
    public static Map<Long, Movie> movies = new HashMap<Long, Movie>();
    public static Map<Long, Genre> genres = new HashMap<Long, Genre>();

    
    public static AtomicLong idGenerator = new AtomicLong(0);
    
    static LocalDate date = LocalDate.of(1946, 9, 11);
    
    static {
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "pepe"));
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "maría"));
        users.put(idGenerator.addAndGet(1), new User(idGenerator.get(), "ramón"));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título1", "body1", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título2", "body2", users.get(2L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título3", "body3", users.get(3L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título4", "body4", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título5", "body5", users.get(2L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título6", "body6", users.get(1L)));
        posts.put(idGenerator.addAndGet(1), new Post(idGenerator.get(), "título7", "body7", users.get(3L)));
        movies.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "1934"  , date, "EEUU", 245, 213, "El Padrino", false, "sadasdas qe qwe qwe qwd" ));
        movies.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "1969"  , date, "EEUU", 4543, 343, "El señor de los anillos", false, "sadasdasd qeqw eqq eqw q" ));
        movies.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "1935"  , date, "Spain", 356, 3454, "ET el extraterrestre", false, "asjkdaskjdhaskjdh" ));
        genres.put(idGenerator.addAndGet(1), new Genre(idGenerator.get(), "Comedia"));
        genres.put(idGenerator.addAndGet(1), new Genre(idGenerator.get(), "Acción"));
    }
}

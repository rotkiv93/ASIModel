package es.udc.lbd.asi.restexample.model.repository;

import java.time.LocalDate;
import java.util.List;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.User;

public interface MovieDAO {

    List<Movie> findAll(Boolean isAdmin);
    
    List<Movie> findAllWithOptions(MovieEnum tipo, User usuario);
    
    List<Movie> findAllInDate(LocalDate fecha);
    
    Movie findById(Long id);

    void save(Movie movie);
    
    void deleteById(Long id);
}

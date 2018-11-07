package es.udc.lbd.asi.restexample.model.repository;

import java.time.LocalDate;
import java.util.List;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public interface MovieDAO {

    List<Movie> findAll(Boolean isAdmin);
    
    List<Movie> findAllInDate(LocalDate fecha);
    
    Movie findById(Long id);

    void save(Movie movie);
    
    void deleteById(Long id);
}

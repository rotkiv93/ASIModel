package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.Set;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public interface MovieDAO {

    List<Movie> findAll();

    Movie findById(Long id);

    void save(Movie movie);
    
    void deleteById(Long id);
}

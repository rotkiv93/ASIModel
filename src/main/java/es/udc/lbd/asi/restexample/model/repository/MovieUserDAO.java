package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.MovieUser;


public interface MovieUserDAO {
	List<MovieUser> findAll();

    MovieUser findById(Long id);

    void save(MovieUser movieUser);

    void deleteById(Long id);
    
}

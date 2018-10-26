package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Genre;

public interface GenreDAO {

	 	List<Genre> findAll();

	    Genre findById(Long id);

	    void save(Genre genre);

	    void deleteById(Long id);

}

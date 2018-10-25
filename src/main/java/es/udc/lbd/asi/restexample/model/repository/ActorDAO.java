package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.Set;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public interface ActorDAO {
	List<Actor> findAll();

	Set<Movie> findForMovie(Long idActor);
	
    Actor findById(Long id);

    void save(Actor actor);

    void deleteById(Long id);

}

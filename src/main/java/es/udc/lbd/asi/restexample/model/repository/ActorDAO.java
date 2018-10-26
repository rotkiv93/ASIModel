package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Actor;

public interface ActorDAO {
	List<Actor> findAll();
	
    Actor findById(Long id);

    void save(Actor actor);

    void deleteById(Long id);

}

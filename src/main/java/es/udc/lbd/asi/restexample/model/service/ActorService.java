package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ActorService {
	
	@Autowired
	private ActorDAO actorDAO;
	
	public List<ActorDTO> findAll() {
		return actorDAO.findAll().stream().map(actor -> new ActorDTO(actor)).collect(Collectors.toList());
	}
	
	public ActorDTO findById(Long id) {
		return new ActorDTO(actorDAO.findById(id));
	}
	
	@Transactional(readOnly = false)
	public ActorDTO save(Actor actor) {
		Actor bdActor = new Actor(actor.getNombre(), actor.getApellido1(), actor.getApellido2(), actor.getPeliculas());
		
		actorDAO.save(bdActor);
		return new ActorDTO(bdActor);
	}
	
	@Transactional(readOnly = false)
	public ActorDTO update(Actor actor) {
		Actor bdActor = actorDAO.findById(actor.getId());
		bdActor.setNombre(actor.getNombre());
		bdActor.setApellido1(actor.getApellido1());
		bdActor.setApellido2(actor.getApellido2());
		bdActor.setPeliculas(actor.getPeliculas());
		
		actorDAO.save(bdActor);
		return new ActorDTO(bdActor);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		actorDAO.deleteById(id);
	}
}

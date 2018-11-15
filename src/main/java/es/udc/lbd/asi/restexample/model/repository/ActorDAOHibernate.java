package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class ActorDAOHibernate extends GenericDAOHibernate implements ActorDAO {

	@Override
	public List<Actor> findAll() {
		return getSession().createQuery("from Actor a order by a.nombre,a.apellido1,a.apellido2").list();
	}

	@Override
	public Actor findById(Long id) {
		return (Actor) getSession().createQuery("from Actor a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(Actor actor) {
		Session s = getSession(); 
		
		try {
			//se añade la pelicula a las listas de actores
			for(Movie a : actor.getPeliculas()) {
				Movie mov1 = (Movie) s.get(Movie.class, a.getId());
				mov1.getActores().add(actor);
				s.saveOrUpdate(mov1);
			}
			s.saveOrUpdate(actor);
		} catch (Exception e){
			
		}
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
	}
	
}

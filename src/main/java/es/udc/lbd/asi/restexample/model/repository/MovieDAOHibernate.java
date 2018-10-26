package es.udc.lbd.asi.restexample.model.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class MovieDAOHibernate extends GenericDAOHibernate implements MovieDAO {

	@Override
	public List<Movie> findAll() {
		return getSession().createQuery("from Movie").list();
	}

	@Override
	public Movie findById(Long id) {
		return (Movie) getSession().createQuery("from Movie m where m.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(Movie movie) {
		Session s = getSession(); 
		
		try {
			//se añade la pelicula a las listas de actores
			for(Actor a : movie.getActores()) {
				Actor act1 = (Actor) s.get(Actor.class, a.getId());
				act1.getPeliculas().add(movie);
				s.saveOrUpdate(act1);
			}
			
			
			//se añaden a la lista de movieuser
			
			
			s.saveOrUpdate(movie);
		} catch (Exception e){
			
		}
	}

	@Override
	public void deleteById(Long id) {
		Session s = getSession(); 
		
		try {
			Movie m = (Movie) s.get(Movie.class, id);
			
			//se borran las listas de peliculas de actores
			if (m !=null) {
				for(Actor a : m.getActores()) {
					Actor act1 = (Actor) s.get(Actor.class, a.getId());
					act1.getPeliculas().remove(m);
					s.saveOrUpdate(act1);
				}
			}
			
			//se borran de movieuser
			
			
			
			//se borra la pelicula
			s.delete(findById(id));
		} catch (Exception e) {
			
		}
	}
	
}

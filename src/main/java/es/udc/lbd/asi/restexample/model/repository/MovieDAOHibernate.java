package es.udc.lbd.asi.restexample.model.repository;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class MovieDAOHibernate extends GenericDAOHibernate implements MovieDAO {

	@Override
	public List<Movie> findAll(Boolean isAdmin) {
		if (isAdmin) return getSession().createQuery("from Movie").list();
		else return getSession().createQuery("from Movie m where m.oculta = false").list();
	}

	
	@Override
	public List<Movie> findAllWithOptions(MovieEnum tipo, User usuario) {
		return getSession().createQuery("select m from Movie m INNER JOIN m.usuarios mu where m.oculta = false AND mu.usuario = :usuario AND mu.estado = :tipo").setParameter("usuario", usuario).setParameter("tipo", tipo).list();
	}
	
	@Override
	public List<Movie> findAllInDate(LocalDate fecha) {
		return getSession().createQuery("from Movie m where m.fecha_estreno = :fecha").setParameter("fecha", fecha, TemporalType.DATE).list();
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
		
			//se añaden la pelicula a movieUser
			for(MovieUser a : movie.getUsuarios()){
				MovieUser mu1 = (MovieUser) s.get(MovieUser.class, a.getId());
				
				mu1.setPelicula(movie);	
				s.saveOrUpdate(mu1);
			}
			
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
				
				for(MovieUser a : m.getUsuarios()) {
					MovieUser mov1 = (MovieUser) s.get(MovieUser.class, a.getId());
					mov1.setPelicula(null);
					mov1.getUsuario().getPeliculas().remove(mov1);
				
					s.delete(mov1);
				}
			}
			
			//se borra la pelicula
			s.delete(findById(id));
		} catch (Exception e) {
			
		}
	}
	
}

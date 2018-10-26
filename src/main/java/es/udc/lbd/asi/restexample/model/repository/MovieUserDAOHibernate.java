package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class MovieUserDAOHibernate extends GenericDAOHibernate implements MovieUserDAO {

	@Override
	public List<MovieUser> findAll() {
		return getSession().createQuery("from movieUser").list();	
	}

	@Override
	public MovieUser findById(Long id) {
		return (MovieUser) getSession().createQuery("from MovieUser m where m.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(MovieUser movieUser) {
		Session s = getSession(); 
		
		try {
			
			User u = s.get(User.class, (movieUser.getUsuario().getId()));
			u.getPeliculas().add(movieUser);
			s.saveOrUpdate(u);
			
			Movie m = s.get(Movie.class, movieUser.getPelicula().getId());
			m.getUsuarios().add(movieUser);
			s.saveOrUpdate(m);
			
			
			s.saveOrUpdate(movieUser);
		} catch (Exception e){
		}
	}

	@Override
	public void deleteById(Long id) {
		Session s = getSession(); 
		
		try {
			MovieUser mov_usr = (MovieUser) s.get(MovieUser.class, id);
			
			User u = s.get(User.class, (mov_usr.getUsuario().getId()));
			u.getPeliculas().remove(mov_usr);
			s.saveOrUpdate(u);
			
			Movie m = s.get(Movie.class, mov_usr.getPelicula().getId());
			m.getUsuarios().remove(mov_usr);
			s.saveOrUpdate(m);
			
			s.delete(mov_usr.getId());
		} catch (Exception e){
		}
		
	}

}

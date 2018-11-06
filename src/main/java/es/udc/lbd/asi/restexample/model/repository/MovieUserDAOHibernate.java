package es.udc.lbd.asi.restexample.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
		return getSession().createQuery("from MovieUser").list();	
	}


	@Override
	public List<MovieUser> findAllMovieUsersWithMoviePending(Movie movie) {
		Query q = getSession().createQuery("from MovieUser m where m.pelicula = :movie and estado = 1");
		q.setParameter("movie", movie);
		return q.list();
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
			
			if (mov_usr !=null) {
			
				User u = s.get(User.class, (mov_usr.getUsuario().getId()));
				u.getPeliculas().remove(mov_usr);
				s.saveOrUpdate(u);
				
				Movie m = s.get(Movie.class, mov_usr.getPelicula().getId());
				m.getUsuarios().remove(mov_usr);
				s.saveOrUpdate(m);
			}
			
			s.delete(findById(id));
		} catch (Exception e){
		}
	}


	@Override
	public MovieUser findByUserAndMovie(Movie movie, User user) {
		return (MovieUser) getSession().createQuery("from MovieUser m where m.pelicula = :movie AND m.usuario = :user").setParameter("movie", movie).setParameter("user", user).uniqueResult();
	}

}

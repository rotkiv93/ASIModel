package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

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
		getSession().saveOrUpdate(movie);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));	
	}
}

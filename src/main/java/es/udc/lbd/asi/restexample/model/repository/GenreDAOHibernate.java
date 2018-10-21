package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class GenreDAOHibernate extends GenericDAOHibernate implements GenreDAO {

	@Override
	public List<Genre> findAll() {
		return getSession().createQuery("from Genre").list();	
	}

	@Override
	public Genre findById(Long id) {
		return (Genre) getSession().createQuery("from Genre g where g.id = :id").setParameter("id", id).uniqueResult();
		
	}

	@Override
	public void save(Genre genre) {
		getSession().saveOrUpdate(genre);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
	}
}

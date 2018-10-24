package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;


import es.udc.lbd.asi.restexample.model.domain.MovieUser;
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
		getSession().saveOrUpdate(movieUser);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));	
	}

}

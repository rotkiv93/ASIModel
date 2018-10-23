package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class DirectorDAOHibernate extends GenericDAOHibernate implements DirectorDAO {

	@Override
	public List<Director> findAll() {
		return getSession().createQuery("from Director").list();	
	}

	@Override
	public Director findById(Long id) {
		return (Director) getSession().createQuery("from Director d where d.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(Director director) {
		getSession().saveOrUpdate(director);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
	}

}

package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class ActorDAOHibernate extends GenericDAOHibernate implements ActorDAO {

	@Override
	public List<Actor> findAll() {
		return getSession().createQuery("from Actor").list();
	}

	@Override
	public Actor findById(Long id) {
		return (Actor) getSession().createQuery("from Actor a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(Actor actor) {
		getSession().saveOrUpdate(actor);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
	}
	
}

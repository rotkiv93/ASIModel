package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class UserDAOHibernate extends GenericDAOHibernate implements UserDAO {

	@Override
	public List<User> findAll() {
		return getSession().createQuery("from User").list();	
	}

	@Override
	public User findById(Long id) {
		return (User) getSession().createQuery("from User m where m.id = :id").setParameter("id", id).uniqueResult();
		
	}

	@Override
	public void save(User user) {
		getSession().saveOrUpdate(user);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));	
	}

}

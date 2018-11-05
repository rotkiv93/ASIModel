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
		Session s = getSession(); 
		
		try {
			for(MovieUser a : user.getPeliculas()){
				MovieUser mu1 = (MovieUser) s.get(MovieUser.class, a.getId());
				
				mu1.setUsuario(user);	
				s.saveOrUpdate(mu1);
			}
		  	s.saveOrUpdate(user);
		}
		catch (Exception e){
			
		}
	}

	@Override
	public void deleteById(Long id) {
		Session s = getSession(); 
		
		try{
			User m = (User) s.get(User.class, id);
			
			for(MovieUser a : m.getPeliculas()) {
				MovieUser mov1 = (MovieUser) s.get(MovieUser.class, a.getId());
				mov1.setUsuario(null);
				mov1.getPelicula().getUsuarios().remove(mov1);
				s.delete(mov1);
			}
			
			s.delete(findById(id));	
		} catch (Exception e){
			
		}
	}

	@Override
	public User findByLogin(String login) {
		return (User) getSession().createQuery("from User where login = :login").setParameter("login", login)
                .uniqueResult();
	}
}

package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.User;

public interface UserDAO {
		List<User> findAll();

	    User findById(Long id);

	    void save(User user);

	    void deleteById(Long id);

}

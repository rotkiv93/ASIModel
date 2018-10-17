package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import org.springframework.stereotype.Repository;


import es.udc.lbd.asi.restexample.model.domain.Movie;

@Repository
public class MovieDAOImplementation implements MovieDAO {

	@Override
	public List<Movie> findAll() {
		return null;
	}

	@Override
	public Movie findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie save(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

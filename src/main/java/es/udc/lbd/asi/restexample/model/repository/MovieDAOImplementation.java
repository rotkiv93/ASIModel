package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;


import es.udc.lbd.asi.restexample.model.domain.Movie;

@Repository
public class MovieDAOImplementation implements MovieDAO {

	@Override
	public List<Movie> findAll() {
		 return InMemoryDB.movies.values().stream().collect(Collectors.toList());
	}

	@Override
	public Movie findById(Long id) {
		   return InMemoryDB.movies.get(id);
	}

	@Override
	public Movie save(Movie movie) {
		 if (movie.getId() == null) {
	            movie.setId(InMemoryDB.idGenerator.addAndGet(1));
	        }
	        InMemoryDB.movies.put(movie.getId(), movie);
	        return movie;
	}

	@Override
	public void deleteById(Long id) {
		InMemoryDB.movies.remove(id);
	}

}

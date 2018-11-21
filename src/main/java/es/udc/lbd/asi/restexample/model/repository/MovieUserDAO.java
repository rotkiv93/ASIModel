package es.udc.lbd.asi.restexample.model.repository;

import java.time.LocalDate;
import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;


public interface MovieUserDAO {
	List<MovieUser> findAll();

    List<MovieUser> findAllMovieUsersWithMoviePending(Movie movie);
    
    List<MovieUser> findAllByLogin(User user);
    
	MovieUser findById(Long id);

    void save(MovieUser movieUser);

    void deleteById(Long id);
    
    MovieUser findByUserAndMovie(Movie movie, User user);

}

package es.udc.lbd.asi.restexample.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieUserService {

		@Autowired
	    private MovieUserDAO movieUserDAO;
	    
		@Autowired
	    private MovieDAO movieDAO;
		
		@Autowired
	    private UserDAO userDAO;
		
	    public List<MovieUserDTO> findAll() {
	    	 return movieUserDAO.findAll().stream().map(movieUser -> new MovieUserDTO(movieUser)).collect(Collectors.toList());
	    }

	    public MovieUserDTO findById(Long id) {
	        return new MovieUserDTO(movieUserDAO.findById(id));
	    }
	    
	    
	    public MovieUserDTO findByMovieAndUSer(Long movieID, String userLogin){
	    	Movie movie = movieDAO.findById(movieID);
	    	User user = userDAO.findByLogin(userLogin);
	    	
	    	return new MovieUserDTO(movieUserDAO.findByUserAndMovie(movie, user));
	    }

	    @Transactional(readOnly = false)
	    public MovieUserDTO save(MovieUserDTO movieUser) {
	    	  MovieUser bdMovieUser = new MovieUser(movieUser.getValoracion(), movieUser.getEstado());
	          bdMovieUser.setUsuario(userDAO.findById(movieUser.getUsuario().getId()));
	          bdMovieUser.setPelicula(movieDAO.findById(movieUser.getPelicula().getId()));
	          
	          movieUserDAO.save(bdMovieUser);
	          return new MovieUserDTO(bdMovieUser);
	    }

	    @Transactional(readOnly = false)
	    public MovieUserDTO update(MovieUserDTO movieUser) {
	        MovieUser bdMovieUser = movieUserDAO.findById(movieUser.getId());
	        bdMovieUser.setValoracion(movieUser.getValoracion());
	        bdMovieUser.setEstado(movieUser.getEstado());
	        bdMovieUser.setPelicula(movieDAO.findById(movieUser.getPelicula().getId()));
	        bdMovieUser.setUsuario(userDAO.findById(movieUser.getUsuario().getId()));
	        
	        movieUserDAO.save(bdMovieUser);
	        return new MovieUserDTO(bdMovieUser);
	    }
	    
	    @Transactional(readOnly = false)
	    public void deleteById(Long id) {
	        movieUserDAO.deleteById(id);
	    }
		
}

package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;

import es.udc.lbd.asi.restexample.model.service.dto.MovieUserDTO;


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
	    
	    public List<MovieUserDTO> findAllByLogin(String userLogin) {
	    	if(userDAO.findByLogin(userLogin) != null){
	    		User user = userDAO.findByLogin(userLogin);
	    	 	 return movieUserDAO.findAllByLogin(user).stream().map(movieUser -> new MovieUserDTO(movieUser)).collect(Collectors.toList());
	    	} else return null;
	    	
	   }
	    
	    
	    
	    public MovieUserDTO findByMovieAndUSer(Long movieID, String userLogin){
	    		Movie movie = movieDAO.findById(movieID);
		    	User user = userDAO.findByLogin(userLogin);
		    	MovieUser pelicula = movieUserDAO.findByUserAndMovie(movie, user);
		    	
		    	if (pelicula != null)
		    		return new MovieUserDTO(pelicula);
		    	else
		    		return null;
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
	    
	    @Transactional(readOnly = false, noRollbackFor = Exception.class)
	    public void deleteById(Long id) {
	        movieUserDAO.deleteById(id);
	    }
		
}

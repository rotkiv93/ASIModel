package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;
    
    @Autowired
    private GenreDAO genreDAO;
    
    @Autowired
    private DirectorDAO directorDAO;
    
    @Autowired
    private ActorDAO actorDAO;
    

    public List<MovieDTO> findAll() {
    	 return movieDAO.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    public MovieDTO findById(Long id) {
        return new MovieDTO(movieDAO.findById(id));
    }

    @Transactional(readOnly = false)
    public MovieDTO save(MovieDTO movie) {
    	  Movie bdMovie = new Movie(movie.getTitulo(), movie.getProductora(), movie.getFecha_estreno(), movie.getPais(),movie.getDuracion(), movie.getAno_salida(), null, movie.getSinopsis());
    	  bdMovie.setOculta(false);
          bdMovie.setGenero(genreDAO.findById(movie.getGenero().getId()));
          bdMovie.setDirector(directorDAO.findById(movie.getDirector().getId()));
          bdMovie.setRutaImagen(movie.getRuta());
          
          Set<Actor> actors = new HashSet<>();
          for(ActorDTO a: movie.getActores()){
        	  actors.add(actorDAO.findById(a.getId()));              
          }
          bdMovie.setActores(actors);
          
          movieDAO.save(bdMovie);
          return new MovieDTO(bdMovie);
    }

    @Transactional(readOnly = false)
    public MovieDTO update(MovieDTO movie) {
        Movie bdMovie = movieDAO.findById(movie.getId());
        bdMovie.setTitulo(movie.getTitulo());
        bdMovie.setProductora(movie.getProductora());
        bdMovie.setFecha_estreno(movie.getFecha_estreno());
        bdMovie.setPais(movie.getPais());
        bdMovie.setDuracion(movie.getDuracion());
        bdMovie.setAno_salida(movie.getAno_salida());
        bdMovie.setSinopsis(movie.getSinopsis());
        bdMovie.setOculta(movie.getOculta());
        bdMovie.setGenero(genreDAO.findById(movie.getGenero().getId()));
        bdMovie.setDirector(directorDAO.findById(movie.getDirector().getId()));
        bdMovie.setRutaImagen(movie.getRuta());
        
        Set<Actor> actors = new HashSet<>();
        for(ActorDTO a: movie.getActores()){
      	  actors.add(actorDAO.findById(a.getId()));              
        }
        bdMovie.setActores(actors);
         
        movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
    }
    
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        movieDAO.deleteById(id);
        
    }
	
}

package es.udc.lbd.asi.restexample.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.udc.lbd.asi.restexample.config.Properties;
import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.security.SecurityUtils;

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
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private Properties properties;
   
    private Path location;
    
    @PostConstruct
    public void initMovieService() {
        this.location = Paths.get(properties.getResourcePath());
        try {
            Files.createDirectories(this.location);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<MovieDTO> findAll() {
    	
    	if (SecurityUtils.getCurrentUserLogin() != null){
    		User usuario = userDAO.findByLogin(SecurityUtils.getCurrentUserLogin());
    	
    		if (usuario.getAuthority() == UserAuthority.ADMIN)
        		return movieDAO.findAll(true).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
        	else
        		return movieDAO.findAll(false).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    	} else{
    		return movieDAO.findAll(false).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    	}	
    }
    
    public List<MovieDTO> findAllWithOptions(MovieEnum tipoBusqueda) {
    	
    	if (SecurityUtils.getCurrentUserLogin() != null){
    		User usuario = userDAO.findByLogin(SecurityUtils.getCurrentUserLogin());
    	
        	return movieDAO.findAllWithOptions(tipoBusqueda, usuario).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    	} else {
    		return null;
    	}
    }
    

    public MovieDTO findById(Long id) {
        return new MovieDTO(movieDAO.findById(id));
    }

    @Transactional(readOnly = false)
    public MovieDTO save(MovieDTO movie) {
    	  Movie bdMovie = new Movie(movie.getTitulo(), movie.getProductora(), movie.getFecha_estreno(), movie.getPais(),movie.getDuracion(), movie.getAno_salida(), movie.getOculta(), movie.getSinopsis());
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
    
    public void store(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new Exception(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.location.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            throw new Exception("Failed to store file " + filename, e);
        }
    }
   
    public Path load(String path) {
        return location.resolve(path);
    }
   
    public Resource getImageAsResource(String fileName) throws Exception {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new Exception(
                        "Could not read file: " + fileName);
 
            }
        }
        catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + fileName, e);
        }
    }    
	
    public List<MovieDTO> findByTitle(String title){
    	return movieDAO.findByTitle(title).stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }
}

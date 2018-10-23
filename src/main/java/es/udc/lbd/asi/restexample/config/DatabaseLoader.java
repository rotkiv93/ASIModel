package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;


	@Configuration
	public class DatabaseLoader {
	    @Autowired
	    private MovieDAO movieService;

	    @Autowired
	    private GenreDAO genreService;
	   
	    @Autowired
	    private DirectorDAO directorService;
	    
	    @Autowired
	    private DatabaseLoader databaseLoader;

	    /*
	     * Para hacer que la carga de datos sea transacional, hay que cargar el propio
	     * objeto como un bean y lanzar el método una vez cargado, ya que en el
	     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
	     * transaciones.
	     */
	    @PostConstruct
	    public void init() {
	        databaseLoader.loadData();
	    }

	    @Transactional(readOnly = false, rollbackFor = Exception.class)
	    public void loadData() {
	        
	    	LocalDate date = LocalDate.of(1946, 9, 11);
	    	genreService.save(new Genre("Comedia"));
	    	genreService.save(new Genre("Terror"));
	    
	    	directorService.save(new Director("Steven", "Spielberg",""));
	    	directorService.save(new Director("Alfred", "Hitchcock",""));
	    	directorService.save(new Director("Martin", "Scorsese",""));
	    	
	    	
	    	movieService.save(new Movie("El Padrino","LucasArts" , date, "EEUU", 245, 213, false, "mafia y mas mafia y tiros y mas tiros", genreService.findById(1L),directorService.findById(3L)));
	    	movieService.save(new Movie("Eduardo Manostijeras","LucasArts2" , date, "Spain", 47, 30, false, "un tio que se corta sin querer",genreService.findById(2L), directorService.findById(2L)));
	    	movieService.save(new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L)));
	    		
	    }
	
}

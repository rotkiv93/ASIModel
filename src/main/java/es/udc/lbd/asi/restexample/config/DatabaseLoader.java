package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
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
	    private ActorDAO actorService;
	    
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
	    	
	    	Actor actor1 = new Actor("Leonardo","Di","Caprio");
	    	Actor actor2 = new Actor("Arnold","Gromenahuer",null);
	    	Actor actor3 = new Actor("Al","Pa","Chino");
	    	
	    	Set <Actor> actoresP1 = new HashSet<>();
	    	actoresP1.add(actor1);
	    	actoresP1.add(actor2);
	    	
	    	Set <Actor> actoresP2 = new HashSet<>();
	    	actoresP2.add(actor1);
	    	actoresP2.add(actor2);
	    	actoresP2.add(actor3);
	    	
	    	actorService.save(actor1);
	    	actorService.save(actor2);
	    	actorService.save(actor3);

	    	movieService.save(new Movie("El Padrino","LucasArts" , date, "EEUU", 245, 213, false, "mafia y mas mafia y tiros y mas tiros", genreService.findById(1L),directorService.findById(3L), actoresP1));
	    	movieService.save(new Movie("Eduardo Manostijeras","LucasArts2" , date, "Spain", 47, 30, false, "un tio que se corta sin querer",genreService.findById(2L), directorService.findById(2L), actoresP1));
	    	movieService.save(new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2));
	    		
	    }
	
}

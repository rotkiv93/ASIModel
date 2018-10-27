package es.udc.lbd.asi.restexample.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieUserDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.UserService;


	@Configuration
	public class DatabaseLoader {
		 private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
		
	    @Autowired
	    private MovieDAO movieService;

	    @Autowired
	    private GenreDAO genreService;
	   
	    @Autowired
	    private DirectorDAO directorService;
	    
	    @Autowired
	    private ActorDAO actorService;
	    
	    @Autowired
	    private MovieUserDAO movieUserService;
	    
	    @Autowired 
	    private UserDAO userDAO;
	    
	    @Autowired
	    private UserService userService;
	    
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
	        try {
	            databaseLoader.loadData();
	        } catch (UserLoginExistsException e) {
	            logger.error(e.getMessage(), e);
	        }
	    }

	    @Transactional(readOnly = false, rollbackFor = Exception.class)
	    public void loadData() throws UserLoginExistsException {
	        
	    	LocalDate date = LocalDate.of(1946, 9, 11);
	    	
	    	//CREATING GENRES
	    	genreService.save(new Genre("Comedia"));
	    	genreService.save(new Genre("Terror"));
	    
	    	
	    	//CREATING DIRECTORS
	    	directorService.save(new Director("Steven", "Spielberg",""));
	    	directorService.save(new Director("Alfred", "Hitchcock",""));
	    	directorService.save(new Director("Martin", "Scorsese",""));
	    	directorService.save(new Director("Juan Antonio", "García","Bayona"));

	    	//CREATING ACTORS
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

	    	
	    	//CREATING USERS
	    	userService.registerUser("pablo", "pablo", true);
	    	userService.registerUser("jaime", "jaime", true);
	    	userService.registerUser("josete", "josete", false);
	    	
	    	
	    	//CREATING MOVIES
	    	Movie pelicula1 = new Movie("El Padrino","LucasArts" , date, "EEUU", 245, 213, false, "mafia y mas mafia y tiros y mas tiros", genreService.findById(1L),directorService.findById(3L), actoresP1);
	    	movieService.save(pelicula1);
	    	Movie pelicula2 = new Movie("Eduardo Manostijeras","LucasArts2" , date, "Spain", 47, 30, false, "un tio que se corta sin querer",genreService.findById(2L), directorService.findById(2L), actoresP1);
	    	movieService.save(pelicula2);
	    	Movie pelicula4 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula4);
	    	Movie pelicula5 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula5);
	    	Movie pelicula6 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula6);
	    	Movie pelicula7 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula7);
	    	Movie pelicula8 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula8);
	    	Movie pelicula9 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula9);
	    	Movie pelicula10 = new Movie("Origen","THX" , date, "Spain", 445, 34, false, "movidas de sueños raros y eso",genreService.findById(2L),directorService.findById(1L), actoresP2);
	    	movieService.save(pelicula10);
	    
	    	
	    	//VOTING MOVIES
	    	movieUserService.save(new MovieUser(userDAO.findById(1L), movieService.findById(1L), 7, MovieEnum.Vista));
	    	movieUserService.save(new MovieUser(userDAO.findById(1L), movieService.findById(2L), 3, MovieEnum.Vista));

	    	movieUserService.save(new MovieUser(userDAO.findById(2L), movieService.findById(2L), 5, MovieEnum.Vista));
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(3L), null, MovieEnum.Pendiente));
	    	
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(3L), 4, MovieEnum.Vista));
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(1L), null, MovieEnum.Pendiente));
	    	
	    }
	
}

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
	    	LocalDate hoy = LocalDate.now();
	    	
	    	//CREATING GENRES
	    	genreService.save(new Genre("Romantico"));
	    	genreService.save(new Genre("Drama"));
	    	
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
	    	userService.registerUser("pablo", "pablo","pablestal@gmail.com", true);
	    	userService.registerUser("jaime", "jaime","james@gmail.com", true);
	    	userService.registerUser("josete", "josete","victorlamas93@gmail.com", false);
	    	
	    	
	    	//CREATING MOVIES
	    	Movie pelicula1 = new Movie("The Godfather","Paramount Pictures" , date, "EEUU", 175, 1972, false, 
	    			"América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe de una de las cinco familias de la mafia de Nueva York...", 
	    			genreService.findById(1L),directorService.findById(3L), actoresP1,"https://image.tmdb.org/t/p/w300/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg");
	    	movieService.save(pelicula1);
	    	Movie pelicula2 = new Movie("Inception","Warner Bros. Pictures" , date, "EEUU",148, 2010, false, 
	    			"Dom Cobb (DiCaprio) es un experto en el arte de apropiarse, durante el sueño, de los secretos del subconsciente ajeno...",
	    			genreService.findById(2L), directorService.findById(2L), actoresP1,"https://image.tmdb.org/t/p/w300/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg");
	    	movieService.save(pelicula2);
	    	Movie pelicula4 = new Movie("Titanic","Paramount Pictures" , date, "EEUU", 195,1997, false, 
	    			"Jack (DiCaprio), un joven artista, gana en una partida de cartas un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido....",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/kHXEpyfl6zqn8a6YuozZUujufXf.jpg");
	    	movieService.save(pelicula4);
	    	Movie pelicula5 = new Movie("Star Wars","Lucas Films" , hoy, "EEUU", 121,1977, false, 
	    			"La princesa Leia, líder del movimiento rebelde que desea reinstaurar la República en la galaxia en los tiempos ominosos del Imperio, es capturada por las Fuerzas Imperiales, capitaneadas por el implacable Darth Vader....",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/btTdmkgIvOi0FFip1sPuZI2oQG6.jpg");
	    	movieService.save(pelicula5);
	    	Movie pelicula6 = new Movie("Harry Potter","THX" , hoy, "EEUU", 152,2001, false, 
	    			"El día en que cumple once años, Harry Potter se entera de que es hijo de dos destacados hechiceros, de los que ha heredado poderes mágicos...",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/sdEOH0992YZ0QSxgXNIGLq1ToUi.jpg");
	    	movieService.save(pelicula6);
	    	Movie pelicula7 = new Movie("Mad Max","Warner Bros. Pictures" , date, "EEUU", 120, 2015, false, 
	    			"Perseguido por su turbulento pasado, Mad Max cree que la mejor forma de sobrevivir es ir solo por el mundo. Sin embargo, se ve arrastrado a formar parte de un grupo que huye a través del desierto en un War Rig ...",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/kqjL17yufvn9OVLyXYpvtyrFfak.jpg");
	    	movieService.save(pelicula7);
	    	Movie pelicula8 = new Movie("Matrix","Warner Bros" , date, "EEUU", 131, 1999, false, 
	    			"Un programador pirata recibe un día una misteriosa visita... ",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/hEpWvX6Bp79eLxY1kX5ZZJcme5U.jpg");
	    	movieService.save(pelicula8);
	    	Movie pelicula9 = new Movie("Up","Pixar Animation Studios" , date, "EEUU", 96, 2009, false, 
	    			"Carl Fredricksen es un viudo vendedor de globos de 78 años que, finalmente, consigue llevar a cabo el sueño de su vida: enganchar miles de globos a su casa y salir volando rumbo a América del Sur...",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/nk11pvocdb5zbFhX5oq5YiLPYMo.jpg");
	    	movieService.save(pelicula9);
	    	Movie pelicula10 = new Movie("Toy Story","Walt Disney Pictures" , date, "EEUU", 80, 1995, false, 
	    			"Los juguetes de Andy, un niño de 6 años, temen que haya llegado su hora y que un nuevo regalo de cumpleaños les sustituya en el corazón de su dueño....",
	    			genreService.findById(2L),directorService.findById(1L), actoresP2,"https://image.tmdb.org/t/p/w300/rhIRbceoE9lR4veEXuwCC2wARtG.jpg");
	    	movieService.save(pelicula10);
	    
	    	
	    	//VOTING MOVIES
	    	movieUserService.save(new MovieUser(userDAO.findById(1L), movieService.findById(1L), null, MovieEnum.Pendiente));
	    	movieUserService.save(new MovieUser(userDAO.findById(1L), movieService.findById(2L), 3, MovieEnum.Vista));
	    	//movieUserService.save(new MovieUser(userDAO.findById(1L), movieService.findById(5L), 5, MovieEnum.Pendiente));
	    	
	    	movieUserService.save(new MovieUser(userDAO.findById(2L), movieService.findById(5L), 5, MovieEnum.Vista));
	    	//movieUserService.save(new MovieUser(userDAO.findById(2L), movieService.findById(4L), null, MovieEnum.Pendiente));
	    	//movieUserService.save(new MovieUser(userDAO.findById(2L), movieService.findById(3L), null, MovieEnum.Pendiente));
	    	
	    	
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(5L), null, MovieEnum.Pendiente));
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(3L), 4, MovieEnum.Vista));
	    	movieUserService.save(new MovieUser(userDAO.findById(3L), movieService.findById(1L), null, MovieEnum.Pendiente));   	
	    }
	
}

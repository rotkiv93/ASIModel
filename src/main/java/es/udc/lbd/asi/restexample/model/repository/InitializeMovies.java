package es.udc.lbd.asi.restexample.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeMovies implements ApplicationRunner {

	@Autowired
	private createMoviesClient moviesClient;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		moviesClient.createMovies();
		
	}

}

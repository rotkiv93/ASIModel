package es.udc.lbd.asi.restexample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.service.MovieService;
import es.udc.lbd.asi.restexample.model.service.PostService;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;

@RestController
@RequestMapping("/api/movie")
public class MovieResource {

	@Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findOne(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) throws IdAndBodyNotMatchingOnUpdateException {
        if (id != movie.getId()) {
            //throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
        }
        return movieService.save(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        movieService.deleteById(id);
    }
	
}

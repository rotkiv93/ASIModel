package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.GenreService;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/genres")
public class GenreResource {

	@Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDTO> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public GenreDTO findOne(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    public GenreDTO save(@RequestBody @Valid Genre genre, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors);
    	return genreService.save(genre);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable Long id, @RequestBody @Valid Genre genre, Errors errors)
    	throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
            errorHandler(errors);
        	if (id != genre.getId()) {
                throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
            }
            return genreService.update(genre);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        genreService.deleteById(id);
    }
	
    
    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
        if (errors.hasErrors()) {
            String errorMsg = errors.getFieldErrors().stream()
                    .map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("; "));
            throw new RequestBodyNotValidException(errorMsg);
        }
    }
}

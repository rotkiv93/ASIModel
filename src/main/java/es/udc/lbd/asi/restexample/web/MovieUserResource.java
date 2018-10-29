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

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.MovieUserService;
import es.udc.lbd.asi.restexample.model.service.dto.MovieUserDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/movieusers")
public class MovieUserResource {

	@Autowired
    private MovieUserService movieUserService;

    @GetMapping
    public List<MovieUserDTO> findAll() {
        return movieUserService.findAll();
    }

    @GetMapping("/{id}")
    public MovieUserDTO findOne(@PathVariable Long id) {
        return movieUserService.findById(id);
    }

    @PostMapping
    public MovieUserDTO save(@RequestBody @Valid MovieUserDTO movieUser, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors);
    	return movieUserService.save(movieUser);
    }

    @PutMapping("/{id}")
    public MovieUserDTO update(@PathVariable Long id, @RequestBody @Valid MovieUserDTO movieUser, Errors errors) 
    		throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
        errorHandler(errors);
    	if (id != movieUser.getId()) {
            throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
        }
        return movieUserService.update(movieUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        movieUserService.deleteById(id);
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
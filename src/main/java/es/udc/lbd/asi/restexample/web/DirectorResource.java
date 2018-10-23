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

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.DirectorService;

import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/directors")
public class DirectorResource {

	@Autowired
	private DirectorService directorService;

	@GetMapping
	public List<DirectorDTO> findAll() {
		return directorService.findAll();
	}

	@GetMapping("/{id}")
	public DirectorDTO findOne(@PathVariable Long id) {
		return directorService.findById(id);
	}

	@PostMapping
	public DirectorDTO save(@RequestBody @Valid Director director, Errors errors) throws RequestBodyNotValidException {
		errorHandler(errors);
		return directorService.save(director);
	}

	@PutMapping("/{id}")
	public DirectorDTO update(@PathVariable Long id, @RequestBody @Valid Director director, Errors errors)
			throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
		errorHandler(errors);
		if (id != director.getId()) {
			throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
		}
		return directorService.update(director);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam Long id) {
		directorService.deleteById(id);
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

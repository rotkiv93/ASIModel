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

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.ActorService;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/actors")
public class ActorResource {

		@Autowired
		private ActorService actorService;
		
		@GetMapping
		public List<ActorDTO> findAll() {
			return actorService.findAll();
		}
		
		@GetMapping("/{id}")
		public ActorDTO findOne(@PathVariable Long id) {
			return actorService.findById(id);
		}
		
		@PostMapping
		public ActorDTO save(@RequestBody @Valid Actor actor, Errors errors) throws RequestBodyNotValidException {
			errorHandler(errors);
			return actorService.save(actor);
		}
		
		 @PutMapping("/{id}")
		 public ActorDTO update(@PathVariable Long id, @RequestBody @Valid Actor actor, Errors errors)
				 throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
			 errorHandler(errors);
			 if (id != actor.getId()) {
				 throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
			 }
			 return actorService.update(actor);
		 }
		 
		 @DeleteMapping("/{id}")
		 public void delete(@RequestParam Long id) {
			 actorService.deleteById(id);
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

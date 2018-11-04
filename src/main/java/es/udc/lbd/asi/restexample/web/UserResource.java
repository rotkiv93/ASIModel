package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public List<UserDTOPublic> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/authority")
	public UserDTOPrivate findOne() {
		return userService.getCurrentUserWithAuthority();
	}
	
	@PostMapping
	public UserDTOPrivate save(@RequestBody @Valid User user, Errors errors) throws RequestBodyNotValidException, UserLoginExistsException {
		errorHandler(errors);
		return userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail());
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

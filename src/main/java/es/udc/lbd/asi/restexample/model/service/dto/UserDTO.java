package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;

public class UserDTO {
	private Long id;
	@NotEmpty
	private String name;
	
	@NotNull
	private Set<MovieUserDTO> peliculas = new HashSet<>();
	
	public UserDTO(){
		
	}
	
	public UserDTO(User usuario){
		this.id = usuario.getId();
		this.name = usuario.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MovieUserDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieUserDTO> peliculas) {
		this.peliculas = peliculas;
	}
	
}

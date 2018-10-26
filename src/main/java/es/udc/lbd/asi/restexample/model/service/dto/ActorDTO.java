package es.udc.lbd.asi.restexample.model.service.dto;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public class ActorDTO {
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido1;
	private String apellido2;
	
	private Set<MovieDTO> peliculas = new HashSet<>();
	
	public ActorDTO() {
		
	}
	
	public ActorDTO(Actor actor) {
		this.id = actor.getId();
		this.nombre = actor.getNombre();
		this.apellido1 = actor.getApellido1();
		this.apellido2 = actor.getApellido2();
		
		
		Set<Movie> pel = actor.getPeliculas();
		for(Movie p : pel){
			this.peliculas.add(new MovieDTO(p));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Set<MovieDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieDTO> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}

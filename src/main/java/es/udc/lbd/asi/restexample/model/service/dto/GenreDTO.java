package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Genre;

public class GenreDTO {
	private Long id;
	@NotEmpty
	private String nombre;
	
	public GenreDTO(){
		
	}
	
	public GenreDTO(Genre genre){
		this.id = genre.getId();
		this.nombre = genre.getNombre();
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
}

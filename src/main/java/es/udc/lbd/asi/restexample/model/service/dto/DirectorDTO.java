package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Director;

public class DirectorDTO {
	
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido1;
	private String apellido2;

	public DirectorDTO(){
	
	}
	
	
	public DirectorDTO(Director director) {
		this.id = director.getId();
		this.nombre = director.getNombre();
		this.apellido1 = director.getApellido1();
		this.apellido2 = director.getApellido2();
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
	
}

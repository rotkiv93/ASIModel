package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;

public class MovieUserDTO {

	private Long id;
	private Integer valoracion;
	

	private MovieEnum estado;
	
	private UserDTOPrivate usuario;

	private MovieDTO pelicula;
	
	public MovieUserDTO(){
		
	}
	
	public MovieUserDTO(MovieUser movieUser) {
		this.id = movieUser.getId();
		this.valoracion = movieUser.getValoracion();
		this.estado = movieUser.getEstado();
		this.usuario = new UserDTOPrivate(movieUser.getUsuario());
		this.pelicula = new MovieDTO(movieUser.getPelicula());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public MovieEnum getEstado() {
		return estado;
	}

	public void setEstado(MovieEnum estado) {
		this.estado = estado;
	}

	public UserDTOPrivate getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTOPrivate usuario) {
		this.usuario = usuario;
	}

	public MovieDTO getPelicula() {
		return pelicula;
	}

	public void setPelicula(MovieDTO pelicula) {
		this.pelicula = pelicula;
	}

}

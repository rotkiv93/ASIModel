package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table (name="MOVIEUSER")
public class MovieUser {
	
	@Id
	@SequenceGenerator(name="movieUserId", sequenceName="id_movieUser_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="movieUserId")
	@Column(name="ID_MOVIEUSER")
	private Long id;
	
	@Column(name= "VALORACION")
	private Integer valoracion;
	
	@Column(name= "ESTADO", nullable = false)
	private MovieEnum estado;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Movie pelicula;

	public MovieUser(){
		
	}
	
	public MovieUser(User usuario,  Movie pelicula, Integer valoracion, MovieEnum estado) {
		super();
		this.valoracion = valoracion;
		this.estado = estado;
		this.usuario = usuario;
		this.pelicula = pelicula;
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

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Movie getPelicula() {
		return pelicula;
	}

	public void setPelicula(Movie pelicula) {
		this.pelicula = pelicula;
	}
	
	
}

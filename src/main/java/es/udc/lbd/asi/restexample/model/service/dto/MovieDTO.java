package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public class MovieDTO {
	private Long id;
	@NotEmpty
	private String productora;
	
	private LocalDate fecha_estreno;
	@NotEmpty
	private String pais;
	@NotNull
	private Boolean oculta;
	@NotNull
	private Integer duracion;
	@NotNull
	private Integer ano_salida;
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String sinopsis;
	@NotNull
	private GenreDTO genero;
	@NotNull
	private DirectorDTO director;
	@NotNull
	private Set<Actor> actores = new HashSet<>();
	
	
	public MovieDTO(){
		
	}

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.productora = movie.getProductora();
		this.fecha_estreno = movie.getFecha_estreno();
		this.pais = movie.getPais();
		this.duracion = movie.getDuracion();
		this.ano_salida = movie.getAno_salida();
		this.titulo = movie.getTitulo();
		this.sinopsis = movie.getSinopsis();
		this.oculta = movie.getOculta();
		this.genero = new GenreDTO(movie.getGenero());
		this.director = new DirectorDTO(movie.getDirector());
		
		//esto probablemente haya que cambiarlo
		Set<Actor> act = new HashSet<>();
		act.addAll(movie.getActores());
		this.actores = act;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public LocalDate getFecha_estreno() {
		return fecha_estreno;
	}

	public void setFecha_estreno(LocalDate fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getAno_salida() {
		return ano_salida;
	}

	public void setAno_salida(Integer ano_salida) {
		this.ano_salida = ano_salida;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public GenreDTO getGenero() {
		return genero;
	}

	public void setGenero(GenreDTO genero) {
		this.genero = genero;
	}

	public DirectorDTO getDirector() {
		return director;
	}

	public void setDirector(DirectorDTO director) {
		this.director = director;
	}

	public Boolean getOculta() {
		return oculta;
	}

	public void setOculta(Boolean oculta) {
		this.oculta = oculta;
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
	}
	
}

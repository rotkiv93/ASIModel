package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="MOVIE")
public class Movie {
	
	@Id
	@SequenceGenerator(name="movieId", sequenceName="id_movie_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="movieId")
	@Column(name="ID_MOVIE")
	private Long id;
	
	private String productora;
	private LocalDate fecha_estreno;
	private String pais;
	private Integer duracion;
	private Integer ano_salida;
	private String titulo;
	private Boolean oculta;
	private String sinopsis;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Director director;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Genre genero;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<MovieUser> usuarios = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "MOVIE_ACTOR",
			joinColumns = {@JoinColumn(name = "movie_id")},
			inverseJoinColumns = {@JoinColumn (name = "actor_id")}
			)
	private Set<Actor> actores = new HashSet<>();
	
	public Movie(){
	}
		
	public Movie(String titulo, String productora, LocalDate fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			 Boolean oculta, String sinopsis) {
		super();
		this.productora = productora;
		this.fecha_estreno = fecha_estreno;
		this.pais = pais;
		this.duracion = duracion;
		this.ano_salida = ano_salida;
		this.titulo = titulo;
		this.oculta = oculta;
		this.sinopsis = sinopsis;
	}

	public Movie(String titulo, String productora, LocalDate fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			 Boolean oculta, String sinopsis, Genre genero) {
		
		this(titulo, productora, fecha_estreno, pais, duracion, ano_salida, oculta, sinopsis);
		this.genero = genero;
	}
	
	public Movie(String titulo, String productora, LocalDate fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			 Boolean oculta, String sinopsis, Genre genero, Director director) {
		this(titulo, productora, fecha_estreno, pais, duracion, ano_salida, oculta, sinopsis, genero);
		this.director = director;
	}
	
	public Movie(String titulo, String productora, LocalDate fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			 Boolean oculta, String sinopsis, Genre genero, Director director, Set<Actor> actores) {
		this(titulo, productora, fecha_estreno, pais, duracion, ano_salida, oculta, sinopsis, genero, director);
		this.actores = actores;
	}

	public Movie(String titulo, String productora, LocalDate fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			 Boolean oculta, String sinopsis, Genre genero, Director director, Set<Actor> actores,Set<MovieUser> usuarios) {
		this(titulo, productora, fecha_estreno, pais, duracion, ano_salida, oculta, sinopsis, genero, director, actores);
		this.usuarios = usuarios;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="PRODUCTORA", nullable = false)
	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	@Column(name="FECHA_ESTRENO", nullable = false)
	public LocalDate getFecha_estreno() {
		return fecha_estreno;
	}

	public void setFecha_estreno(LocalDate fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}


	@Column(name="PAIS", nullable = false)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	@Column(name="DURACION", nullable = false)
	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}


	@Column(name="ANO_SALIDA", nullable = false)
	public Integer getAno_salida() {
		return ano_salida;
	}

	public void setAno_salida(Integer ano_salida) {
		this.ano_salida = ano_salida;
	}


	@Column(name="TITULO", nullable = false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	@Column(name="OCULTA", nullable = false)
	public Boolean getOculta() {
		return oculta;
	}

	public void setOculta(Boolean oculta) {
		this.oculta = oculta;
	}


	@Column(name="SINOPSIS", nullable = false)
	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
	public Genre getGenero() {
		return genero;
	}

	public void setGenero(Genre genero) {
		this.genero = genero;
	}

	
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public void setActores(Set<Actor> actores) {
		this.actores = actores;
	}

	
	public Set<MovieUser> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<MovieUser> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", productora=" + productora + ", fecha_estreno=" + fecha_estreno + ", pais=" + pais
				+ ", duracion=" + duracion + ", ano_salida=" + ano_salida + ", titulo=" + titulo + ", oculta=" + oculta
				+ ", sinopsis=" + sinopsis + "]";
	}

}

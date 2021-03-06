package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	//private List<Actor> Actores;
	//private Director director;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Genre genero;


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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", productora=" + productora + ", fecha_estreno=" + fecha_estreno + ", pais=" + pais
				+ ", duracion=" + duracion + ", ano_salida=" + ano_salida + ", titulo=" + titulo + ", oculta=" + oculta
				+ ", sinopsis=" + sinopsis + "]";
	}

}

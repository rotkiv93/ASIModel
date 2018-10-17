package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Date fecha_estreno;
	private String pais;
	private Integer duracion;
	private Integer ano_salida;
	private String titulo;
	private Boolean oculta;
	private String sinopsis;
	//private List<Actor> Actores;
	//sdklajdlkasjdlkasjd
	//kjslkajsdlkdj
	
	public Movie(){	
	}
		
	public Movie(Long id, String productora, Date fecha_estreno, String pais, Integer duracion, Integer ano_salida,
			String titulo, Boolean oculta, String sinopsis) {
		super();
		this.id = id;
		this.productora = productora;
		this.fecha_estreno = fecha_estreno;
		this.pais = pais;
		this.duracion = duracion;
		this.ano_salida = ano_salida;
		this.titulo = titulo;
		this.oculta = oculta;
		this.sinopsis = sinopsis;
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

	@Column(name="FECHA_ESTREN", nullable = false)
	public Date getFecha_estreno() {
		return fecha_estreno;
	}

	public void setFecha_estreno(Date fecha_estreno) {
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
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", productora=" + productora + ", fecha_estreno=" + fecha_estreno + ", pais=" + pais
				+ ", duracion=" + duracion + ", ano_salida=" + ano_salida + ", titulo=" + titulo + ", oculta=" + oculta
				+ ", sinopsis=" + sinopsis + "]";
	}

}

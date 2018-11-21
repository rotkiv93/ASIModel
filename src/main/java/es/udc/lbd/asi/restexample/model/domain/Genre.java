package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table (name="GENRE")
public class Genre {
	
	@Id
	@SequenceGenerator(name="genreId", sequenceName="id_genre_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genreId")
	@Column(name="ID_GENRE")
	private Long id;
	
    @Column(name = "NOMBRE",unique = true, nullable = false)
    @NotEmpty
	private String nombre;
	
	public Genre(){
		
	}

	public Genre(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="NOMBRE", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nombre=" + nombre + "]";
	}
	
}

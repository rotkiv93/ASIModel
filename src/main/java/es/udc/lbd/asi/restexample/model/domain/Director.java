package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="DIRECTOR")
public class Director {

	@Id
	@SequenceGenerator(name="directorId", sequenceName="id_director_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="directorId")
	@Column(name="ID_DIRECTOR")
	private Long id;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name="APELLIDO1", nullable = false)
	private String apellido1;
	
	@Column(name="APELLIDO2", nullable = true)
	private String apellido2;

	public Director(){
		
	}
	
	public Director(String nombre, String apellido1, String apellido2) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
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

	@Override
	public String toString() {
		return "Director [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ "]";
	}
	
	
}

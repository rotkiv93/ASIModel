package es.udc.lbd.asi.restexample.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="ACTOR")
public class Actor {
	
	@Id
	@SequenceGenerator(name="actorId", sequenceName="id_actor_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="actorId")
	@Column(name="ID_ACTOR")
	private Long id;
	
    @Column(name = "NOMBRE", nullable = false)
	private String nombre;
    
    @Column(name = "APELLIDO1", nullable = false)
    private String apellido1;
    
    @Column(name = "APELLIDO2", nullable = true)
    private String apellido2;
    
    @ManyToMany(mappedBy = "actores", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Movie> peliculas = new HashSet<>();

	public Actor() {
    }
    
	   public Actor(String nombre, String apellido1, String apellido2){
	    	super();
	    	this.nombre = nombre;
	    	this.apellido1 = apellido1;
	    	this.apellido2 = apellido2;
	    }
	
	
    public Actor(String nombre, String apellido1, String apellido2, Set<Movie> peliculas){
    	super();
    	this.nombre = nombre;
    	this.apellido1 = apellido1;
    	this.apellido2 = apellido2;
    	this.peliculas = peliculas;
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

	public Set<Movie> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Movie> peliculas) {
		this.peliculas = peliculas;
	}

}

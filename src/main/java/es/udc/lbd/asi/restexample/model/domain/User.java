package es.udc.lbd.asi.restexample.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="USERS")
public class User {
	
	@Id
	@SequenceGenerator(name="userId", sequenceName="id_user_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userId")
	@Column(name="ID_USER")
    private Long id;
	
	@Column(name = "NOMBRE", nullable = false)
    private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<MovieUser> peliculas = new HashSet<>();

    public User() {
    }

    public User(String name) {
        super();
        this.name = name;
    }
    
    public User(String name, Set<MovieUser> peliculas) {
    	this(name);
    	this.peliculas = peliculas;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Set<MovieUser> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieUser> peliculas) {
		this.peliculas = peliculas;
	}
    
    
}

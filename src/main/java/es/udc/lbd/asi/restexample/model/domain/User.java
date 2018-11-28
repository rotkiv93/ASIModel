package es.udc.lbd.asi.restexample.model.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	private String email;
	
	 @Column(unique = true)
	 private String login;

	 private String password;

	 @Enumerated(EnumType.STRING)
	 private UserAuthority authority;
	 
	 @Column(nullable = false)
	 private LocalDate fecha_alta;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<MovieUser> peliculas = new HashSet<>();

    public User() {
    }

    public User(String login, String password,String email, LocalDate fecha_alta){
    	this.login = login;
    	this.password = password;
    	this.email = email;
    	this.authority = UserAuthority.USER;
    	this.fecha_alta = fecha_alta;
    }
    
    
    public User(String login, String password,String email, LocalDate fecha_alta, UserAuthority aut){
    	this(login,password,email, fecha_alta);
    	this.authority = aut;
    }
    
    
    public User(String login, String password, String email, LocalDate fecha_alta,UserAuthority aut, Set<MovieUser> peliculas) {
    	this(login,password, email, fecha_alta,aut);
    	this.peliculas = peliculas;
    }
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

	public Set<MovieUser> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieUser> peliculas) {
		this.peliculas = peliculas;
	}

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
    
    
}

package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;

public class UserDTOPrivate {
    private Long id;
    private String login;
    private String password;
    private UserAuthority authority;
    private String email;

	@NotNull
	private Set<MovieUserDTO> peliculas = new HashSet<>();
    
    public UserDTOPrivate() {
    }

    public UserDTOPrivate(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        // la contraseña no se rellena, nunca se envía al cliente
        this.authority = user.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<MovieUserDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieUserDTO> peliculas) {
		this.peliculas = peliculas;
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
}

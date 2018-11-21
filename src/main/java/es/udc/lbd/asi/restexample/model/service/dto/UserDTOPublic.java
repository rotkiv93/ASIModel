package es.udc.lbd.asi.restexample.model.service.dto;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;

public class UserDTOPublic {
    private Long id;
    @NotEmpty
    private String login;
	
    private Set<MovieUserDTO> peliculas = new HashSet<>();
    
    public UserDTOPublic() {
    }

    public UserDTOPublic(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        
		Set<MovieUser> act = user.getPeliculas();
		for(MovieUser a : act){
			this.peliculas.add(new MovieUserDTO(a));
		}
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

	public Set<MovieUserDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<MovieUserDTO> peliculas) {
		this.peliculas = peliculas;
	}
    
    
}
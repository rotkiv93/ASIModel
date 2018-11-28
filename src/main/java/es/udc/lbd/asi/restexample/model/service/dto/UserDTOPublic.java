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
    
    private Integer num_vistas;
  
    
    public UserDTOPublic() {
    }

    public UserDTOPublic(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        
        this.num_vistas = user.getPeliculas().size();
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

	public Integer getNum_vistas() {
		return num_vistas;
	}

	public void setNum_vistas(Integer num_vistas) {
		this.num_vistas = num_vistas;
	}
    
}
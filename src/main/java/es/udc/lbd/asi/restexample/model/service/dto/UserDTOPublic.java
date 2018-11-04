package es.udc.lbd.asi.restexample.model.service.dto;


import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.User;

public class UserDTOPublic {
    private Long id;
    @NotEmpty
    private String login;
	

    public UserDTOPublic() {
    }

    public UserDTOPublic(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
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
}
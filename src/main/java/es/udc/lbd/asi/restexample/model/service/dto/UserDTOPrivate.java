package es.udc.lbd.asi.restexample.model.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.MovieEnum;
import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.NotifEnum;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;

public class UserDTOPrivate {
    private Long id;
    private String login;
    private String password;
    private UserAuthority authority;
    private String email;
    private LocalDate fecha_alta;
    private Integer num_vistas = 0;
    private Integer num_pendientes = 0;
    private Integer num_valoradas = 0;
    private NotifEnum notificaciones;
    
    
	@NotNull
	private Set<MovieUserDTO> peliculas = new HashSet<>();
    
    public UserDTOPrivate() {
    }

    public UserDTOPrivate(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.fecha_alta = user.getFecha_alta();
        // la contraseña no se rellena, nunca se envía al cliente
        this.authority = user.getAuthority();
        this.notificaciones = user.getNotificacion();
        
        Set<MovieUser> pel = user.getPeliculas();
        for (MovieUser p :pel){
        	if(p.getEstado() == MovieEnum.Vista) this.num_vistas ++;
          	if(p.getEstado() == MovieEnum.Pendiente) this.num_pendientes ++;
          	if(p.getValoracion() != null) this.num_valoradas ++;
        }        
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

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Integer getNum_vistas() {
		return num_vistas;
	}

	public void setNum_vistas(Integer num_vistas) {
		this.num_vistas = num_vistas;
	}

	public Integer getNum_pendientes() {
		return num_pendientes;
	}

	public void setNum_pendientes(Integer num_pendientes) {
		this.num_pendientes = num_pendientes;
	}

	public Integer getNum_valoradas() {
		return num_valoradas;
	}

	public void setNum_valoradas(Integer num_valoradas) {
		this.num_valoradas = num_valoradas;
	}

	public NotifEnum getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(NotifEnum notificaciones) {
		this.notificaciones = notificaciones;
	}
	
	
}

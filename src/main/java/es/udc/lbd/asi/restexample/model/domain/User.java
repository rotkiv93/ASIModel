package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public User() {
    }

    public User(String name) {
        super();
        this.name = name;
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
}

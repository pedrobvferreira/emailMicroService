package com.ms.email.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author Pedro Ferreira
 **/
@Entity
@Table(name = "user_model")
public class UserModel implements Serializable {

	private static final long serialVersionUID = -3013122315662714947L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String email;
    
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
	
	@Transient
	public String toString() {
		return "User{" + "id=" + this.id + '\'' + ", email='" + this.email + '\'' + '}';
	}

//	@Transient
//	public static UserModel convert(UserDto userDto) {
//		return new ModelMapper().map(userDto, UserModel.class);
//	}

}

package com.ms.user.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","email"})
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 5988032895424885148L;
	
	@NotNull
	@JsonProperty("id")
	private Long id;
	@NotBlank
	@JsonProperty("name")
    private String name;
	@NotBlank
	@Email
	@JsonProperty("email")
    private String email;
	@NotBlank
    private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

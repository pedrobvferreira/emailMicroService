package com.ms.email.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Pedro Ferreira
 **/
@JsonPropertyOrder({"id","email"})
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 5988032895424885148L;
	
	@JsonProperty("id")
	private Long id;
	@NotBlank
	@Email
	@JsonProperty("email")
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
}

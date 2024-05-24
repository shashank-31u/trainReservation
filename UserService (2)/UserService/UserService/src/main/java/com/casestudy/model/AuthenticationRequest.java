package com.casestudy.model;

import org.springframework.data.annotation.Id;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Authreq")
public class AuthenticationRequest {
	@Id
	@Column(name="Username")
	@NotEmpty(message = "Username cannot be blank")
	private String username;
	
	@Column(name="Password")
	@NotEmpty(message = "Password cannot be blank")
	private String password;

}

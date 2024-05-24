package com.casestudy.model;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	@Id
	@Column(name="Username")
	@NotEmpty(message = "Username cannot be blank")
	private String username;
	@Column(name="Password")
	@NotEmpty(message = "Username cannot be blank")
	private String password;

}

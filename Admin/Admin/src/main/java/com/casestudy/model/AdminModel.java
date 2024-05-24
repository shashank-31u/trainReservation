package com.casestudy.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Admin")
public class AdminModel {
	
	@Id
	@Column(name="Id")
	@NotEmpty(message = "Id cannot be blank")
	private String id;
	
	@Column(name="Username")
	@NotEmpty(message = "username cannot be blank")
	private String username;
	
	@Column(name="Password")
	@NotEmpty(message = "Password cannot be blank")
	private String password;

}

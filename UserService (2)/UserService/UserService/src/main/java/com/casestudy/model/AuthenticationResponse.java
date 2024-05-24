package com.casestudy.model;





import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Authres")
public class AuthenticationResponse {
	@Id
	@Column(name="Jwt")
	private String jwt;
	
	@Column(name="Success")
	private String success;
	
	
	
	
	

}

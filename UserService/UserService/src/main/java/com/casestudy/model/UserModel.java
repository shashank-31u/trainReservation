package com.casestudy.model;


import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")
public class UserModel {
	@Id
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Full Name")
	private String fullName;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Phone Number")
	private String phoneNumber;
	
	@Column(name="Age")
	private int age;
	
	@Column(name="Bank Name")
	private String bankName;
	
	@Column(name="Account No")
	private String accountNo;
	
	
	
	

}

package com.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.model.AdminModel;


public interface AdminRepository extends JpaRepository<AdminModel, String> {

	public AdminModel findByUsername(String username);
	
	

}

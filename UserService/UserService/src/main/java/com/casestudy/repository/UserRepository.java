package com.casestudy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>{
	
	Optional<UserModel> findById(String UserId);
	UserModel delete(String userId);
	UserModel update(String userId);
	
	
}

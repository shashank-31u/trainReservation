package com.casestudy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{
	
	public Optional<UserModel> findByUserId(String UserId);
	
    public void deleteById(String UserId);
    
//    public List<UserModel> findAll();

//	public UserModel remove(String userId);

	

	

	
 
	
}

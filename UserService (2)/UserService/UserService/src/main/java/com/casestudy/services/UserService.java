package com.casestudy.services;

import java.util.List;

import com.casestudy.model.UserModel;

public interface UserService {
//	to save the user
	public UserModel addUser(UserModel userModel);
	
//	to get all the user
	public List<UserModel> getAllUser();
	
//	get user By id;
//	public UserModel getUser(String UserId);
	public UserModel getUser(String UserId);
	
//	delete user
	public String deleteById(String UserId);

	public UserModel save(UserModel userModel);
	
//	public UserModel delete(String userId);
	
	

	
	
	
	
	

}

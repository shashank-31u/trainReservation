package com.casestudy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.model.UserModel;
import com.casestudy.repository.UserRepository;
@Service
public class UserImpl implements UserService {
	
	List<UserModel> list;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserModel addUser(UserModel userModel) {
		list.add(userModel);
		return userModel;
	}

	@Override
	public List<UserModel> getAllUser() {
		
		return list;
	}

	@Override
	public UserModel getUser(String UserId) {
		
		return userRepo.findById(UserId).get();	 
	}

	@Override
	public UserModel deleteUser(String UserId) {
		// TODO Auto-generated method stub
		return userRepo.delete(UserId);
	}

	@Override
	public UserModel updateUser(String UserId) {
		// TODO Auto-generated method stub
		return userRepo.update(UserId);
	}

	

	

}

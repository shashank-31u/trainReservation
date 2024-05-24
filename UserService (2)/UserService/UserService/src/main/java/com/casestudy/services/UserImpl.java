package com.casestudy.services;

import java.util.List;
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
	public UserModel getUser(String userId) {
		
		return userRepo.findById(userId).get();	 
	}



	@Override
	public UserModel save(UserModel userModel) {
		// TODO Auto-generated method stub
		return userRepo.save(userModel);
	}

	@Override
	public String deleteById(String userId) {
		// TODO Auto-generated method stub
		userRepo.deleteById(userId);
		return userId;
	}

	





	



	

	

}

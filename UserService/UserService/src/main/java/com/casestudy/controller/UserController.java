package com.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.UserModel;
import com.casestudy.services.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	Rest API to get all User Details
	
	@GetMapping("/viewallusers")
	public List<UserModel> getUser() {

		return userService.getAllUser();

	}
	
	// Rest API to get User details by Id
	@GetMapping("/users/{userId}")
	public UserModel getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}
//	Rest API to add user User Details
	@PostMapping("/users")
	public UserModel addUser(@RequestBody UserModel userModel) {
		return userService.addUser(userModel);
	}
//	Rest API to delete User profile by Id
	@DeleteMapping("/users/{userId}")
	public UserModel deleteCourse(@PathVariable String userId) {
		return userService.deleteUser(userId);
	}
//	Rest API to update User details by Id
	@PutMapping("/users/{userId}")
	public UserModel updateUser(@PathVariable String userId, @RequestBody UserModel userModel) {
		return userService.updateUser(userId);
	}
	
	

}

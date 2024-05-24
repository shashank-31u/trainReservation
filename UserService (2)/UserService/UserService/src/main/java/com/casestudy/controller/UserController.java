package com.casestudy.controller;



import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.UserModel;
import com.casestudy.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
	

	


//	}
//	Rest API to update User details by Id
	@RequestMapping(value="/updateuserprofile/{userId}", method=RequestMethod.PUT)
	public UserModel updateUser(@Valid @PathVariable String userId, @RequestBody UserModel userModel) {
		//logger implementation
        logger.info("[updateprofile/Id] info message added");
        logger.debug("[updateprofile/Id] debug message added");
		return userService.save(userModel);
	}
	
	
	
	
	
	
	
	
	
	
//	Rest API to get all User Details
//	
//	@GetMapping("/viewallusers")
//	public List<UserModel> getUser() {
//
//		return userService.getAllUser();
//
//	}
	
	
	
	
	


}

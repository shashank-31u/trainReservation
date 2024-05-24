package com.casestudy.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.casestudy.model.AuthenticationRequest;
import com.casestudy.model.AuthenticationResponse;
import com.casestudy.model.UserModel;
import com.casestudy.repository.UserRepository;
import com.casestudy.services.JwtUtil;
import com.casestudy.services.UserInfoService;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	AuthenticationManager authenticationmanager;
	
	@Autowired
	UserInfoService userinfoservice;
	
	@Autowired
	JwtUtil jwtutil;
	
	//Adding Logger 
	org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
 
	
	//Rest API to Authenticate as User
	
	@PostMapping("/authenticateuser")
	private ResponseEntity<?> authenticateClientToken(@RequestBody AuthenticationRequest authrequest) throws Exception{

		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		}
		
		catch(Exception e) {
			String success = "false";
			return ResponseEntity.ok(new AuthenticationResponse("invalid credentials",success));
		}
		
		UserDetails userdetails= userinfoservice.loadUserByUsername(authrequest.getUsername());
		
		String jwt = jwtutil.generateToken(userdetails);
		String success = "true";
		//logger implementation
		 logger.info("[authenticate] info message added");
	     logger.debug("[authenticate] debug message added");
		
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt ,success));
	}
	
	
	//Rest API to get all User details
	@GetMapping("/viewalluserprofile")
	public List<UserModel> getuser(){
		
		//logger implementation
        logger.info("[viewalluserprofile] info message added");
        logger.debug("[viewalluserprofile] debug message added");
        
		return userrepo.findAll();
	}
	
	@GetMapping("/byuserid/{userId}")
	public Optional<UserModel> getUserId(@PathVariable String userId) {
		logger.info("[/byuserid/{userId}] info message added");
        logger.debug("[/byuserid/{userId}] debug message added");
		
		return userrepo.findByUserId(userId);
		}
	
	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}
	

	@PostMapping("/registeruser")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserModel userModel, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        // Build a more structured response for validation errors
	        StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
	        for (ObjectError error : bindingResult.getAllErrors()) {
	            errorMessage.append(error.getDefaultMessage()).append("; ");
	        }

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
	    }

	    userrepo.save(userModel);
	    return ResponseEntity.ok("User with Id: " + userModel.getUserId() + " has been Registered Successfully");
	}
	
	@RequestMapping(value="/deleteuser/{userId}", method=RequestMethod.DELETE)
	public String delete(@PathVariable String userId) {
		 userrepo.deleteById(userId);
		//logger implementation
	     logger.info("[deleteuser/userId] info message added");
	     logger.debug("[deleteuser/userId] debug message added");
		 return "User with id "+userId+" have been deleted";
	}
}
	


package com.casestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.AdminModel;
import com.casestudy.model.AuthenticationRequest;
import com.casestudy.model.AuthenticationResponse;
import com.casestudy.repository.AdminRepository;
import com.casestudy.service.UserInfoService;
import com.casestudy.service.JwtUtil;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private AdminRepository adminrepo;
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private UserInfoService adminservice;
	@Autowired
	private AuthenticationManager authenticationmanager; 
	Logger logger= LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}
	
	//Rest API to add/Register as Admin details
		@PostMapping("/register")
		private ResponseEntity<AuthenticationResponse> registerClientToken(@RequestBody AuthenticationRequest authrequest){

			AdminModel adminmodel =new AdminModel();

			adminmodel.setUsername(authrequest.getUsername());
			adminmodel.setPassword(authrequest.getPassword());
			
			try {
				adminrepo.save(adminmodel);
			}
			catch(Exception e){
				return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(), HttpStatus.OK);
			}
			
			//logger implementation
//	        logger.info("[register] info message added");
//	        logger.debug("[register] debug message added");
			
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
					(), HttpStatus.OK);
		}
	
	//Rest API to authenticate Admin details
		@PostMapping("/authenticate")
		private ResponseEntity<?> authenticateClientToken(@RequestBody AuthenticationRequest authrequest) throws Exception{

			try {
				authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
			}
			catch(Exception e) {
				String success="false";
				return ResponseEntity.ok(new AuthenticationResponse("Invalid Credentials",success));
			}
			
			UserDetails userdetails= adminservice.loadUserByUsername(authrequest.getUsername());
			
			String jwt = jwtutil.generateToken(userdetails);
			String success="true";
			//logger implementation
	        logger.info("[authenticate] info message added");
	        logger.debug("[authenticate] debug message added");
	        
			return ResponseEntity.ok(new AuthenticationResponse(jwt,success));
		}
		
		
		
		
	

}

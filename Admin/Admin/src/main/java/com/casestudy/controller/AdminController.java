package com.casestudy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.casestudy.model.AdminModel;
import com.casestudy.model.TrainModel;
import com.casestudy.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AdminRepository adminrepo;
	
	Logger logger= LoggerFactory.getLogger(AdminController.class);
	
	
	//--------------------------------------Admin-CRUD-----------------------------------------
	//Rest API to add Admin details
	@PostMapping("/registeradmin")
	public String addadmin(@Valid @RequestBody AdminModel admin) {
		adminrepo.save(admin);
				

		        
		return "Admin with Id: "+admin.getId()+" have been Registered Successfully";
	}
	
	//Rest API to get Admin details by Id
	@GetMapping("/viewadminprofile/{id}")
	public Optional<AdminModel> getadmin(@PathVariable("id") String id){
	        
		return adminrepo.findById(id);
	}
	
	//Rest API to update Admin details by Id
	@PutMapping("/updateprofile/{id}")
	public String updateadmin(@Valid @PathVariable("id") String id, @RequestBody AdminModel adminmodel) {
		adminrepo.save(adminmodel);
		return "Admin with id "+id+" have been updated successfully";
	}
	
	//Rest API to delete Admin details by Id
	@DeleteMapping("/deleteadmin/{id}")
	public String deleteadmin(@PathVariable String id) {
		
		adminrepo.deleteById(id);
		        
		return "Admin with id "+id+" have been deleted";
	}
	
	//Rest API to get all User details
	@GetMapping("/viewalladminprofile")
	public List<AdminModel> getadmin(){
			
			//logger implementation
	    logger.info("[viewalladminprofile] info message added");
	    logger.debug("[viewalladminprofile] debug message added");
	        
	    return adminrepo.findAll();
	}
	
	
	
}

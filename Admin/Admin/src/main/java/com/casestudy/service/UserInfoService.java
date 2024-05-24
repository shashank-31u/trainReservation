package com.casestudy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.casestudy.model.AdminModel;
import com.casestudy.repository.AdminRepository;

@Service
public class UserInfoService implements UserDetailsService{
	@Autowired
	private AdminRepository adminrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AdminModel availAdmin = adminrepo.findByUsername(username);
		
		if (availAdmin==null) {
			return null;
		}
		String user=availAdmin.getUsername();
		String pass=availAdmin.getPassword();
		return new User(user, pass,new ArrayList<>());
	}

}

package com.java.crudproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.crudproject.entities.UserPrincipal;
import com.java.crudproject.entities.Users;
import com.java.crudproject.repository.UserRepo;

@Service
public class MyUserDetalisService {
	@Service
	public class MyUserDetailsService implements UserDetailsService {

	    @Autowired
	    private UserRepo userRepo;


	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Users user = userRepo.findByUsername(username);
	        if (user == null) {
	            System.out.println("User Not Found");
	            throw new UsernameNotFoundException("user not found");
	        }
	        
	        return new UserPrincipal(user);
	    }
	    
	   
	    
	    
	    
	    
	}
}

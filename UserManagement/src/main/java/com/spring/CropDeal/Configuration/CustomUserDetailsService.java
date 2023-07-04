package com.spring.CropDeal.Configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.CropDeal.Model.User;
import com.spring.CropDeal.Repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
    private UserRepository repository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> credential = repository.findByUserName(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	}
	
}

package com.spring.CropDeal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.CropDeal.Model.User;
import com.spring.CropDeal.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public boolean addUser(User user) {
		
		User savedUser=userRepository.save(user);
		return savedUser!=null;
	}
	
	
	public String encryptPassword(String password) {
		
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String encodedPassword=passwordEncoder.encode(password);
		return encodedPassword;
	}
	
	public String generateToken(String username) {
		
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}

package com.spring.CropDeal.Controller;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CropDeal.Model.SignInRequest;
import com.spring.CropDeal.Model.UpdateUserRequest;
import com.spring.CropDeal.Model.User;
import com.spring.CropDeal.Repository.UserRepository;
import com.spring.CropDeal.Service.JWTService;
import com.spring.CropDeal.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private JWTService jwtService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
			
			
			Optional<User> retrivedUser=userRepository.findByUserName(user.getUserName());
			if(retrivedUser!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
			}
			
			String hashedPassword=userService.encryptPassword(user.getPassword());
			user.setPassword(hashedPassword);
			userRepository.save(user);
			return ResponseEntity.ok("User registered successfully");
	}
	
	

	    @PostMapping("/signin")
	    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
	        
	    	  Optional<User> userOptional = userRepository.findByUserName(signInRequest.getUserName());

	    	    if (userOptional.isEmpty()) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    	    }

	    	    User user = userOptional.get();
	    	    String hashedPassword = userService.encryptPassword(signInRequest.getPassword());

	    	    if (!hashedPassword.equals(user.getPassword())) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    	    }

	    	    return ResponseEntity.ok("Sign-in successful");
	    }
	
	    
	    @PutMapping("update/{userId}")
	    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
	        Optional<User> optionalUser = userRepository.findById(userId);

	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        User user = optionalUser.get();
	        String hashedPassword=userService.encryptPassword(updateUserRequest.getPassword());

			user.setPassword(hashedPassword);
	        user.setUserName(updateUserRequest.getUserName());
	        

	        userRepository.save(user);

	        return ResponseEntity.ok("User updated successfully");
	    }
	    
	    
	    @DeleteMapping("/delete/{userId}")
	    public ResponseEntity<String> deleteAccount(@PathVariable Long userId) {
	        
	        Optional<User> optionalUser = userRepository.findById(userId);
	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	       
	        User user = optionalUser.get();
	        userRepository.delete(user);
	        return ResponseEntity.ok("Account deleted successfully.");
	    }
	    
	    @GetMapping("/token")
	    public String getToken(User user) {
	    	return jwtService.generateToken(user.getUserName());
	    }
	    
	    @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
	    	jwtService.validateToken(token);
	    	return "Token is valid";
	    }

}

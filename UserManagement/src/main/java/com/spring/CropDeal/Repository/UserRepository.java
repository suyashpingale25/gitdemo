package com.spring.CropDeal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.CropDeal.Model.User;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, Long> {
	
	User save(User user);
	Optional<User> findByUserName(String userName);
	
}

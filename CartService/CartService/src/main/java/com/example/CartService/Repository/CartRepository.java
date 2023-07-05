package com.example.CartService.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.CartService.Model.CartItem;

@Repository
public interface CartRepository extends MongoRepository<CartItem, String> {
	
	List<CartItem> findByUserId(Long userId);

	void deleteByCartItemId(String cartItemId);
}

package com.example.CartService.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CartService.Model.CartItem;
import com.example.CartService.Repository.CartRepository;

@Service
public class CartService {
	
private final CartRepository cartRepository;
    
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    
    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    
    public CartItem addToCart(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }
    
    public void removeFromCart(String cartItemId) {
        cartRepository.deleteByCartItemId(cartItemId);
    }
	
}

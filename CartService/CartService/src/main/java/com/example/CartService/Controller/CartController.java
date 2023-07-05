package com.example.CartService.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CartService.Model.CartItem;
import com.example.CartService.Service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cartItems")
@Api(value = "Cart Service")
public class CartController {
	
private final CartService cartService;
    
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping("/getCartItems/{userId}")
    @ApiOperation(value = "getCartItems ")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }
    
    @PostMapping("/addTocart")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartService.addToCart(cartItem);
    }
    
    @DeleteMapping("/deleteCartItem/{cartItemId}")
    public void removeFromCart(@PathVariable String cartItemId) {
        cartService.removeFromCart(cartItemId);
    }
    
}

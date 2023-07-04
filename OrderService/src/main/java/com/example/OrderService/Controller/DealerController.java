package com.example.OrderService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderService.DTO.OrderRequest;
import com.example.OrderService.Model.OrderLineItems;
import com.example.OrderService.Service.OrderService;
import java.util.ArrayList;

@RestController
@RequestMapping("/Order")
public class DealerController {
	
	
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
		
		List<OrderLineItems> items=orderRequest.getItems();
		String userId=orderRequest.getUserId();
		return orderService.placeOrder(items,userId);
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable String orderNumber){
		
		return orderService.deleteOrder(orderNumber);
	}
	
}

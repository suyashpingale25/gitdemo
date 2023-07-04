package com.example.OrderService.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.OrderService.DTO.InventoryResponse;
import com.example.OrderService.DTO.OrderRequest;
import com.example.OrderService.Model.OrderLineItems;
import com.example.OrderService.Proxy.OrderServiceProxy;
import com.example.OrderService.Repository.OrderRepository;

import com.example.OrderService.Model.Order;

@Service
public class OrderService {
	
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderServiceProxy orderServiceProxy;
	
	public ResponseEntity<String> placeOrder(List<OrderLineItems> items,String userId){
		
		InventoryResponse response=orderServiceProxy.isInStock(items);
		if(response.getStatus()) {
			String orderNumber=UUID.randomUUID().toString();
			
			Order order=new Order();
			order.setOrderLineItemsList(createOrderItems(items,order));
			order.setOrderNumber(orderNumber);
			order.setUserId(userId);
			
			InventoryResponse responce2=orderServiceProxy.placeOrder(items);
			if(response.getStatus()) {
				orderRepository.save(order);
				return ResponseEntity.ok("Order Placed Successfully!!!");
			}
			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order Not Placed.");

	}
		
	public ResponseEntity<String> deleteOrder(String orderNumber){
		
		orderRepository.deleteByOrderNumber(orderNumber);
		return ResponseEntity.ok("Order deleted Successfully!!!");
		
	}
       
	
	private List<OrderLineItems> createOrderItems(List<OrderLineItems> orderItemRequests,Order order) {
	
	    List<OrderLineItems> orderItems = new ArrayList<>();

	    for (OrderLineItems itemRequest : orderItemRequests) {
	        OrderLineItems orderLineItem = new OrderLineItems();
	        orderLineItem.setCropId(itemRequest.getCropId());
	        orderLineItem.setCropName(itemRequest.getCropName());
	        orderLineItem.setQuantity(itemRequest.getQuantity());
	        orderLineItem.setPrice(itemRequest.getPrice());
	        orderLineItem.setPrice(itemRequest.getPrice());
	        orderLineItem.setOrder(order);
	        orderItems.add(orderLineItem);
	    }

	    return orderItems;
	}
	





	
	
}

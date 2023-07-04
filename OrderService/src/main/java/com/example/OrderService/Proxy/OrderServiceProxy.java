package com.example.OrderService.Proxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.OrderService.DTO.OrderRequest;
import com.example.OrderService.Model.OrderLineItems;
import com.example.OrderService.DTO.InventoryResponse;

@FeignClient(name="CropListing",url="http://localhost:8085")
public interface OrderServiceProxy {
	
	@PostMapping("/croplisting/check")
	public InventoryResponse isInStock(@RequestBody List<OrderLineItems> items);
		
	@PostMapping("/croplisting/placeOrder")
	public InventoryResponse placeOrder(@RequestBody List<OrderLineItems> items);
		
	
	
}

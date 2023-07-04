package com.example.OrderService.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.OrderService.Model.OrderLineItems;

public class OrderRequest {
	
	private List<OrderLineItems> items;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<OrderLineItems> getItems() {
		return items;
	}
	public void setItems(List<OrderLineItems> items) {
		this.items = items;
	}

}

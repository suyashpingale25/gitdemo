package com.example.OrderService.Model;




import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_storage")
public class Order {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderLineItems> orderLineItemsList;
	@Id
	private String orderNumber;
	private String userId;
	
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public List<OrderLineItems> getOrderLineItemsList() {
		return orderLineItemsList;
	}
	public Order() {
		super();
	}
	public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}

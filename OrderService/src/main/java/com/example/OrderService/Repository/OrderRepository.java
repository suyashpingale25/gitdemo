package com.example.OrderService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OrderService.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

	
	void deleteByOrderNumber(String orderId);

}

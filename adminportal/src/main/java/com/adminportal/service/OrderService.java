package com.adminportal.service;

import java.util.List;


import com.adminportal.domain.Order;
import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.Book;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Payment;





public interface OrderService {
	

	Order save(Order order);

	List<Order> findAll();

	Order findOne(Long id);


	
}

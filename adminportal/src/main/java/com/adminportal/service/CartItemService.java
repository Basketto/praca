package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Book;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addBookToCartItem(Book book, User user, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}

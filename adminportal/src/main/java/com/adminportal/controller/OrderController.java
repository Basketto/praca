package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.adminportal.domain.User;
import com.adminportal.domain.Order;
import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Payment;


import com.adminportal.service.OrderService;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.service.CartItemService;
import com.adminportal.service.UserService;
import com.adminportal.service.ShoppingCartService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService OrderService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

/*	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookService.save(book);

		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:bookList";
	}*/
	
	@RequestMapping("/orderInfo")
	public String orderInfo(@RequestParam("id") Long id, Model model, Principal principal) {
		
		Order order = OrderService.findOne(id);
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("order", order);
	
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		return "orderInfo";
	}
	
	
	
	@RequestMapping("/orderUpdate")
	public String updateOrder(@RequestParam("id") Long id, Model model) {
		Order order = orderService.findOne(id);
		model.addAttribute("order", order);
		
		return "orderUpdate";
	}
	/*
	@RequestMapping(value="/orderUpdate", method=RequestMethod.POST)
	public String updateOrderPost(@ModelAttribute("order") Order order, HttpServletRequest request) {
		orderService.save(order);
		
		MultipartFile bookImage = book.getBookImage();
		
		if(!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/book/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/book/bookInfo?id="+book.getId();
	}/*
	*/
	@RequestMapping("/orderList")
	public String orderList(Model model) {
		List<Order> orderList = OrderService.findAll();
		model.addAttribute("orderList", orderList);		
		return "orderList";
		
	}/*
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		bookService.removeOne(Long.parseLong(id.substring(8)));
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		
		return "redirect:/book/bookList";
	}*/

}

package com.amanuel.onlinestore.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amanuel.onlinestore.models.Cart;
import com.amanuel.onlinestore.models.Product;
import com.amanuel.onlinestore.models.User;
import com.amanuel.onlinestore.services.UserService;
import com.amanuel.onlinestore.validator.UserValidator;

@Controller
@RequestMapping("api/cart")
public class Carts {
	private UserValidator userValidator;
	private UserService userService;
	
	public Carts(UserService userService, UserValidator userValidator) {
	      this.userService = userService;
	      this.userValidator = userValidator;
	  }
	@RequestMapping("")
	public String cart(Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Double sum = 0.0;
		int cartsize = 0;
		List<Product> product = userService.allProducts();
		model.addAttribute("products", product);
		for(int i = 0; i < product.size(); i++) {
			
			if(product.get(i).getCart() != null) {
				if(product.get(i).getUser().getId() == product.get(i).getCart().getUser().getId()) {
					sum += product.get(i).getPrice();
					cartsize++;
					
					
				}
			}
		}
		model.addAttribute("sum", sum);
		model.addAttribute("cartsize", cartsize);
		System.out.println("sum is: " + sum);
		System.out.println("cart size: " + cartsize);
		return "cartPage";
	}
	//THE PAGE SHOULDN'T REFRESH UPON ADDING A PRODUCT TO CART AND SHOULD STAY IN THE SAME PAGE.
	@PostMapping("/{id}")
	public String saveCart(@Valid @ModelAttribute("cart") Cart cart,@PathVariable("id") Long id, BindingResult result, Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Product product = userService.findProductById(id);
		
		Cart newcart = new Cart();
		newcart.setCart(newcart.getCart()+1);
		newcart.setUser(user);
		userService.saveCart(newcart);
		
		product.setCart(newcart);
		userService.saveProduct(product);
		return "redirect:/";
	}
	@RequestMapping("/delete/{id}")
	public String removeFromCart(@PathVariable("id") Long id, Principal principal, Model model) {
		Product product = userService.findProductById(id);
		Cart cart = userService.findCartById(product.getCart().getId());
		product.setCart(null);
		cart.setUser(null);
		userService.saveCart(cart);
		System.out.println("cart from delete:" + cart);
		return "redirect:/api/cart";
	}
	
}

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
		List<Product> product = userService.allProducts();
		model.addAttribute("products", product);
		return "cartPage";
	}
	//THE PAGE SHOULDING REFRESH UPON ADDING A PRODUCT TO CART AND SHOULD STAY IN THE SAME PAGE.
	@PostMapping("/{id}")
	public String saveCart(@Valid @ModelAttribute("cart") Cart cart,@PathVariable("id") Long id, BindingResult result, Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Product product = userService.findProductById(id);
		cart.setUser(user);
		product.setCart(cart);
		userService.saveCart(cart);
		
		return "redirect:/";
	}
	
}

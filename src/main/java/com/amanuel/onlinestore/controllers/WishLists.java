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

import com.amanuel.onlinestore.models.Product;
import com.amanuel.onlinestore.models.User;
import com.amanuel.onlinestore.models.WishList;
import com.amanuel.onlinestore.services.UserService;
import com.amanuel.onlinestore.validator.UserValidator;
@Controller
@RequestMapping("/api/wishlist")
public class WishLists {
	
	private UserValidator userValidator;
	private UserService userService;
	
	public WishLists(UserService userService, UserValidator userValidator) {
	      this.userService = userService;
	      this.userValidator = userValidator;
	  }
	@RequestMapping("")
	public String wishLists(@Valid @ModelAttribute("wishlist") WishList wishlist, Principal principal, Model model) {
		String email = principal.getName();
		System.out.println("email---: " + email);
		User user = userService.findByEmail(email);
		List<Product> product = userService.allProducts();
		model.addAttribute("products", product);
		return "wishListPage";
	}
// Creating Wish List
	@PostMapping("/{id}")
	public String saveWishList(@Valid @ModelAttribute("wishlist") WishList wishlist, @PathVariable("id") Long id,BindingResult result, Principal principal) {
		System.out.println("helloloollll: ");
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Product product = userService.findProductById(id);
		product.setWishlist(wishlist);
		System.out.println("product1: " + product);
		wishlist.setUser(user);
		
		System.out.println("product: " + product);
		System.out.println("wishlist: " + wishlist);
		userService.saveWishList(wishlist);
		return "redirect:/";
	}
}

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
@RequestMapping("api/wishlist")
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
		
		int cartsize = 0;
		for(int i = 0; i < product.size(); i++) {
			if(product.get(i).getCart() != null) {
				if(product.get(i).getUser().getId() == product.get(i).getCart().getUser().getId()) {
					cartsize++;
				}
			}
		}
		model.addAttribute("cartsize", cartsize);
		return "wishListPage";
	}
// Creating Wish List
	@PostMapping("/{id}")
	public String saveWishList(@Valid @ModelAttribute("wishlist") WishList wishlist, @PathVariable("id") Long id,BindingResult result, Principal principal) {
		System.out.println("helloloollll: ");
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Product product = userService.findProductById(id);
		
		WishList newwish = new WishList();
		newwish.setUser(user);
		userService.saveWishList(newwish);
		
		product.setWishlist(newwish);
		userService.saveWishList(newwish);
		return "redirect:/api/cart";
	}
	@PostMapping("/save/{id}")
	public String saveWishListFromHome(@Valid @ModelAttribute("wishlist") WishList wishlist, @PathVariable("id") Long id,BindingResult result, Principal principal) {
		System.out.println("helloloollll: ");
		String email = principal.getName();
		User user = userService.findByEmail(email);
		Product product = userService.findProductById(id);
		
		WishList newwish = new WishList();
		newwish.setUser(user);
		userService.saveWishList(newwish);
		
		product.setWishlist(newwish);
		userService.saveWishList(newwish);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteWishList(@PathVariable("id") Long id, Principal principal, Model model) {
		Product product = userService.findProductById(id);
		WishList wishlist = userService.findWishListById(product.getWishlist().getId());
		product.setWishlist(null);
		wishlist.setUser(null);
		userService.saveWishList(wishlist);
		return "redirect:/api/wishlist";
	}
}

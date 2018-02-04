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

import com.amanuel.onlinestore.models.Comment;
import com.amanuel.onlinestore.models.Product;
import com.amanuel.onlinestore.models.User;
import com.amanuel.onlinestore.services.UserService;
import com.amanuel.onlinestore.validator.UserValidator;

@Controller
@RequestMapping("/api/products")
public class Products {
	
	private UserValidator userValidator;
	private UserService userService;
	
	public Products(UserService userService, UserValidator userValidator) {
	      this.userService = userService;
	      this.userValidator = userValidator;
	  }
	// Creating products form page
	@RequestMapping("/page")
	public String product(@Valid @ModelAttribute("product") Product product) {
		return "productPage";
	}
	
	@PostMapping("/save")
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		//		userValidator.validate(product, result);
// 		if (result.hasErrors()) {
//         return "redirect:/api/products/save";
//     }
		product.setUser(user);
		userService.saveProduct(product);
		
		System.out.println("product: " + product);
		return "redirect:/api/products/myproducts";
	}
	@RequestMapping("/myproducts")
	public String allProducts(Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		List<Comment> comments = userService.allComment();
		model.addAttribute("comments", comments);
		model.addAttribute("products", user.getProducts());
		return "userProductPage";
	}
	
	@RequestMapping("/{id}")
	public String singleProduct(@Valid @ModelAttribute("com") Comment comment,@PathVariable("id") Long id, Principal principal, Model model) {
		Product product = userService.findProductById(id);
		List<Comment> comments = userService.allComment();
		System.out.println("List of comments:" + comments);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		for(int i = 0; i < product.getComments().size(); i++) {
			System.out.println("iphone comments: " + product.getComments().get(i));
		}
		System.out.println("product page: " + product);
		return "singleProductPage";
	}
	
	
	
	
	
	
	
	
	
}

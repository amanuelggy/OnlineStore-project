package com.amanuel.onlinestore.controllers;

import java.security.Principal;

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
@RequestMapping("/api/comments/")
public class Comments {
	   private UserValidator userValidator;
	   private UserService userService;
	 
	   public Comments(UserService userService, UserValidator userValidator) {
	        this.userService = userService;
	        this.userValidator = userValidator;
	   }
//	   @RequestMapping("")
//	   public String saveComment(@Valid @ModelAttribute("comment") Comment comment) {
//		   return "redirect:/";
//	   }
	   
	   @PostMapping("/save/{id}")
	   public String saveComments(@Valid @ModelAttribute("com") Comment comment,BindingResult result, @PathVariable("id") Long id, Principal principal, Model model) {
		   System.out.println("hello from Comments");
		   String email = principal.getName();
		   User user = userService.findByEmail(email);
		   Product product = userService.findProductById(id);
		   Comment newcomment = new Comment();
		   newcomment.setComment(comment.getComment());
		   newcomment.setUser(user);
		   newcomment.setProduct(product);
		   userService.saveComment(newcomment);
		   
		   
		  // model.addAttribute("product", product);
		   
		   System.out.println("comment: " + newcomment);
		   return "redirect:/api/products/{id}";
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
}

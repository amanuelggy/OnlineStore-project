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
	public String product(@Valid @ModelAttribute("product") Product product, Model model) {
		List<Product> products = userService.allProducts();
		int cartsize = 0;
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getCart() != null) {
				if(products.get(i).getUser().getId() == products.get(i).getCart().getUser().getId()) {
					cartsize++;
				}
			}
		}
		model.addAttribute("cartsize", cartsize);
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
		
		Product pro = new Product();
		pro.setImg(product.getImg());
		pro.setName(product.getName());
		pro.setPrice(product.getPrice());
		pro.setShipfee(product.getShipfee());
		pro.setShipStatus(product.getShipStatus());
		pro.setDescription(product.getDescription());
		pro.setDetail(product.getDetail());
		
		
		pro.setUser(user);
		userService.saveProduct(pro);

		return "redirect:/api/products/myproducts";
	}
	@RequestMapping("/myproducts")
	public String allProducts(Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		List<Comment> comments = userService.allComment();
		List<Product> product = userService.allProducts();
		model.addAttribute("comments", comments);
		model.addAttribute("products", user.getProducts());
		
		int cartsize = 0;
		for(int i = 0; i < product.size(); i++) {
			if(product.get(i).getCart() != null) {
				if(product.get(i).getUser().getId() == product.get(i).getCart().getUser().getId()) {
					cartsize++;
				}
			}
		}
		model.addAttribute("cartsize", cartsize);
		return "userProductPage";
	}
	
	@RequestMapping("/{id}")
	public String singleProduct(@Valid @ModelAttribute("com") Comment comment,@PathVariable("id") Long id, Principal principal, Model model) {
		Product product = userService.findProductById(id);
		List<Comment> comments = userService.allComment();
		System.out.println("List of comments:" + comments);
		List<Product> products = userService.allProducts();
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		
		int cartsize = 0;
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getCart() != null) {
				if(products.get(i).getUser().getId() == products.get(i).getCart().getUser().getId()) {
					cartsize++;
				}
			}
		}
		model.addAttribute("cartsize", cartsize);
		return "singleProductPage";
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Principal principal, Model model) {
		Product product = userService.findProductById(id);
		List<Comment> comment = userService.allComment();
//		for(int i = 0; i < comment.size(); i++) {
//			if(comment.get(i).getUser().getId() == id) {
//				userService.deleteComment(comment.get(i).getId());
//			}
//		}
//		System.out.println("this is from products: " + product);
//		product.setWishlist(null);
//		product.setCart(null);
//		product.setComments(null);
//		product.setUser(null); 
		userService.deleteProductById(id);
		return "redirect:/api/products/myproducts";
	}
	
	
	
	
	
	
	
	
	
}

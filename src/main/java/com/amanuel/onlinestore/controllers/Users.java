package com.amanuel.onlinestore.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amanuel.onlinestore.models.Cart;
import com.amanuel.onlinestore.models.Comment;
import com.amanuel.onlinestore.models.Product;
import com.amanuel.onlinestore.models.Role;
import com.amanuel.onlinestore.models.User;
import com.amanuel.onlinestore.services.UserService;
import com.amanuel.onlinestore.validator.UserValidator;

@Controller
public class Users {
    
	 // NEW
    private UserValidator userValidator;
	private UserService userService;
    
    // NEW
    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user,BindingResult result) {
        return "loginPage";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
    		if (result.hasErrors()) {
            return "loginPage";
        }
    		if(userService.findAllUsers().size() == 0) {
    			userService.saveUserWithAdminRole(user);
    			return "redirect:/login";
    		}
    		else {
        userService.saveWithUserRole(user);
        return "redirect:/login";
    		}
    }
    
    
    @RequestMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @RequestParam(value="loggedin", required=false) String loggedin) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "loginPage";
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(@RequestParam(value = "product", required = false) String product,@RequestParam(value = "allproducts",required = false) String allproducts,Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        List<Cart> cart = userService.allCart();
		List<Product> products = userService.allProducts();
		List<Comment> comment = userService.allComment();
		allproducts = "";
		if(product == null) {
			product = "";
		}
		Double sum = 0.0;
		for(int i = 0; i < products.size(); i++) {
			
			if(products.get(i).getCart() != null && products.get(i).getCart().getUser().getId() == user.getId()) {
				sum += products.get(i).getPrice();
//				model.addAttribute("cartsize", products.get(i).getC);
			}
			
		}
		
		System.out.println("this is the cartsize(): " + user.getCartSize());
//		model.addAttribute("cartsize", cartsize);
		model.addAttribute("cartsize", user.getCartSize());
		//model.addAttribute("cartsize", cartsize);
		//System.out.println("cart size: " + cartsize);
//		int num = 0;
//		for(int i = 0; i < comment.size(); i++) {
//			if(comment.get(i).getProduct().getId() == products.get(i).getId()) {
//				System.out.println("comment " + comment.get(i).getProduct().getId() + " and " + "comment" + products.get(i).getId());
//			}
//		}
		
//		int totalComment = 0;
//		model.addAttribute("totalComment", totalComment);
		model.addAttribute("comments",comment);
		model.addAttribute("allproducts", allproducts);
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		model.addAttribute("currentUser", user);
		model.addAttribute("userId", user.getId());
		List<Role> role = user.getRoles();
		for (int i = 0; i < role.size(); i++) {
	        if(role.get(i).getName().equals("ROLE_ADMIN")) {
	        		
	        		return "redirect:/admin";
	        }
//	        else {
//	        	model.addAttribute("currentUser", user);
//	        return "homePage";
//	        	}
	        
        }
        return "homePage";
    }
    @RequestMapping("/admin")
    public String show(Principal principal, Model model) {
    		String username = principal.getName();
    		
    		List<User> allusers = userService.findAllUsers();
    		model.addAttribute("currentUsers", allusers);
    		model.addAttribute("currentUser", userService.findByEmail(username));
    		return "adminPage";
    }
    @RequestMapping("/user/setting")
    public String setting(Principal principal, Model model) {
    		String email = principal.getName();
        User user = userService.findByEmail(email);
        List<Product> products = userService.allProducts();
        int cartsize = 0;
        for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getCart() != null && products.get(i).getCart().getUser().getId() == user.getId()) {
				cartsize++;
			}
		}
        model.addAttribute("currentUser", user);
        model.addAttribute("cartsize", user.getCartSize());
        return "settingPage";
    }
    @RequestMapping("/main")
    public String main(Model model,@RequestParam(value = "product", required = false) String product,@RequestParam(value = "allproducts",required = false) String allproducts, HttpSession session) {
    		List<Product> products = userService.allProducts();
    		allproducts = "";
    		if(product == null) {
    			product = "";
    		}
    		
		model.addAttribute("products", products);
		session.setAttribute("product", product);
		model.addAttribute("allproducts", allproducts);
		return "mainPage";
    }
    @RequestMapping("/main/{id}")
	public String singleProduct(@Valid @ModelAttribute("com") Comment comment,@PathVariable("id") Long id, Principal principal, Model model) {
    		String email = principal.getName();
    		Product product = userService.findProductById(id);
		List<Comment> comments = userService.allComment();
		System.out.println("List of comments:" + comments);
		List<Product> products = userService.allProducts();
		User user = userService.findByEmail(email);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		
		model.addAttribute("cartsize", user.getCartSize());
		return "mains_singleProductPage";
	}
    @RequestMapping("/main/about")
    public String about(Model model) {
    		return "about";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

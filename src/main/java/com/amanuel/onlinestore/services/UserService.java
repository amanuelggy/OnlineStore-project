package com.amanuel.onlinestore.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amanuel.onlinestore.models.Cart;
import com.amanuel.onlinestore.models.Comment;
import com.amanuel.onlinestore.models.Product;
import com.amanuel.onlinestore.models.User;
import com.amanuel.onlinestore.models.WishList;
import com.amanuel.onlinestore.repositories.CartRepo;
import com.amanuel.onlinestore.repositories.CommentRepo;
import com.amanuel.onlinestore.repositories.ProRepo;
import com.amanuel.onlinestore.repositories.RoleRepository;
import com.amanuel.onlinestore.repositories.UserRepository;
import com.amanuel.onlinestore.repositories.WishRepo;
@Transactional
@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProRepo proRepo;
    private WishRepo wishRepo;
    private CartRepo cartRepo;
    private CommentRepo commentRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository,ProRepo proRepo, WishRepo wishRepo,CartRepo cartRepo,CommentRepo commentRepo, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.proRepo = proRepo;
        this.wishRepo = wishRepo;
        this.cartRepo = cartRepo;
        this.commentRepo = commentRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // Users and Roles
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
    public User findUserById(Long id) {
    		return userRepository.findOne(id);
    }
    
    
    // Products 
    public void saveProduct(Product product) {
    		proRepo.save(product);
    }
    
    public List<Product> allProducts() {
    		return (List<Product>) proRepo.findAll();
    }
    public Product findProductById(Long id) {
    		return proRepo.findOne(id);
    }
    
    // WishList
    public void saveWishList(WishList wishlist) {
    		wishRepo.save(wishlist);
    }
    public List<WishList> allWishLists(){
    		return (List<WishList>) wishRepo.findAll();
    }
    
    // Cart
    public void saveCart(Cart cart) {
    		cartRepo.save(cart);
    }
    public List<Cart> allCart(){
    		return (List<Cart>) cartRepo.findAll();
    }
    
    // Comments
    public void saveComment(Comment comment) {
    		commentRepo.save(comment);
    }
    public List<Comment> allComment(){
    		return (List<Comment>) commentRepo.findAll();
    }
    
    
}

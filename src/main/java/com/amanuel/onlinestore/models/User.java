package com.amanuel.onlinestore.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

//import org.springframework.context.annotation.Role;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Size(min=3, message="Name must be greater than 3 characters")
    private String email;
    private String firstname;
    private String lastname;
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    @Transient
    private String passwordConfirmation;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Product> products;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<WishList> wishLists;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;
    
    public User() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public List<WishList> getWishLists() {
		return wishLists;
	}
	public void setWishLists(List<WishList> wishLists) {
		this.wishLists = wishLists;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
//	public int cartsize( ) {
//		for(int i = 0; i < products.size(); i++) {
//			int cartsize = 0;
//			if(products.get(i).getCart() != null && products.get(i).getCart().getUser().getId() == user.getId()) {
//				cartsize++;
//				sum += products.get(i).getPrice();	
//			}
//		}
//		return false;
//	}
	public int getCartSize() {
		int cart_size = 0;
		for(int i = 1; i < carts.size(); i++) {
			if(id == carts.get(i).getUser().getId()) {
				System.out.println("user id from user: " + id);
				System.out.println("carts.get(i).getUser().getId() from user: " + id);
				cart_size++;
				
			}
			
		}
		return cart_size;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ "]";
	}
    
    
	
}

package com.amanuel.onlinestore.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
	
		@Id
		@GeneratedValue
		private Long id;
		
//		private String img;
//		@Size(min=1, message = "name must be greater than 1 character")
//		private String name;
	//	
//		@Min(0)
//		private Double price;
	//	
//		@Min(0)
//		private Double numOfProd;
		
//		private Double shipfee;
//		private Boolean shipStatus;
//		private String description;
//		private String detail;
		
		@Column(updatable = false)
		private Date createdAt;
		private Date updatedAt;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private User user;
		
		@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	    private List<Product> products;
		
		public Cart() {
			
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

//		public String getName() {
//			return name;
//		}
	//
//		public void setName(String name) {
//			this.name = name;
//		}
	//
//		public Double getPrice() {
//			return price;
//		}
	//
//		public void setPrice(Double price) {
//			this.price = price;
//		}

		

//		public Double getNumOfProd() {
//			return numOfProd;
//		}
	//
	//
//		public void setNumOfProd(Double numOfProd) {
//			this.numOfProd = numOfProd;
//		}

//		public Double getShipfee() {
//			return shipfee;
//		}
	//
//		public void setShipfee(Double shipfee) {
//			this.shipfee = shipfee;
//		}
	//	
//		public String getImg() {
//			return img;
//		}
	//
	//
//		public void setImg(String img) {
//			this.img = img;
//		}
	//
	//
//		public Boolean getShipStatus() {
//			return shipStatus;
//		}
	//
	//
//		public void setShipStatus(Boolean shipStatus) {
//			this.shipStatus = shipStatus;
//		}
	//
	//
//		public String getDescription() {
//			return description;
//		}
	//
//		public void setDescription(String description) {
//			this.description = description;
//		}
	//
//		public String getDetail() {
//			return detail;
//		}
	//
//		public void setDetail(String detail) {
//			this.detail = detail;
//		}

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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}
		
//		@Override
//		public String toString() {
//			return "Cart [id=" + id + ", user=" + user + ", products=" + products + "]";
//		}
}

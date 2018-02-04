package com.amanuel.onlinestore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.onlinestore.models.Cart;

@Repository 
public interface CartRepo extends CrudRepository<Cart, Long> {

}

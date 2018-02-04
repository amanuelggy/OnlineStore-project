package com.amanuel.onlinestore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.onlinestore.models.Product;

@Repository
public interface ProRepo extends CrudRepository<Product, Long> {

}

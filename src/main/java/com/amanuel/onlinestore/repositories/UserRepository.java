package com.amanuel.onlinestore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.onlinestore.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	 User findByEmail(String email);
}

package com.amanuel.loginRegistration.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.loginRegistration.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	 User findByEmail(String email);
}

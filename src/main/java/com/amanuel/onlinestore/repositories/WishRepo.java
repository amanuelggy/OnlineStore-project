package com.amanuel.onlinestore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.onlinestore.models.WishList;

@Repository
public interface WishRepo extends CrudRepository<WishList, Long>{

}

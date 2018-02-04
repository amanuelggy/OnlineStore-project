package com.amanuel.onlinestore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amanuel.onlinestore.models.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Long> {

}

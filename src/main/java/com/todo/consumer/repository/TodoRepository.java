package com.todo.consumer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.todo.consumer.model.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	List<Todo> findAll();
	
	Optional<Todo> findById(Long id);
	
	Todo findByUserId(Long userId);
	
	Todo findByTitle(String title);
	
	
	

}

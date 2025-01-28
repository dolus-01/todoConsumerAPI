package com.todo.consumer.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.consumer.model.request.TodoRequest;
import com.todo.consumer.service.TodoService;

@RestController
public class TodoController {

	private TodoService todoService;
	
	public TodoController(TodoService todo) {
		this.todoService = todo;
	}

	@GetMapping(value = "/json/data")
	public Object getAllData() throws IOException, InterruptedException {
		return todoService.getAllJsonData();
	}

	@GetMapping(value = "/json/data/{id}")
	public Object getData(@PathVariable String id) throws IOException, InterruptedException {
		return todoService.getJsonData(id);
	}

	@PostMapping("/json/data")
	public Object addItemtoTodo(@RequestBody TodoRequest todo) {
		return "Hello, " + todo.getUserId();
	}

	@DeleteMapping("/json/user/{userId}")
	public void deleteUser(@PathVariable("userId") String id) {
		// Delete the user in this method with the id.
	}

	@PutMapping("/json/user")
	public void updateUser(@RequestParam("userid") String userid, @RequestBody TodoRequest todo) {
		// inside this method we have to update the user record
	}

}

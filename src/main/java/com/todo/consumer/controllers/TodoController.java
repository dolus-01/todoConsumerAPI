package com.todo.consumer.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}

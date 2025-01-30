package com.todo.consumer.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(value = "/json/data/todo/{id}")
	public Object getSavedData(@PathVariable String id) throws IOException, InterruptedException {
		return todoService.getSavedData(id);
	}

	@PostMapping(value = "/json/data")
	public Object saveNewData(@RequestBody TodoRequest request) {

		return todoService.saveNewData(request);
	}

	@PatchMapping(value = "/json/data/{id}")
	public Object udateJsonData(@PathVariable String id, @RequestBody TodoRequest request) {
		return todoService.updateData(id,request);
	}

	@DeleteMapping(value = "/json/data/{id}")
	public Object deleteJsonData(@PathVariable String id) {
		return todoService.deleteData(id);

	}

}

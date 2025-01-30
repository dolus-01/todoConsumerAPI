package com.todo.consumer.service;

import java.io.IOException;

import com.todo.consumer.model.request.TodoRequest;

public interface TodoService {
	public Object getAllJsonData() throws IOException, InterruptedException;
	public Object getJsonData(String id) throws IOException, InterruptedException;
	public Object saveNewData(TodoRequest request);
	public Object updateData(String id, TodoRequest request);
	public Object deleteData(String id);
}

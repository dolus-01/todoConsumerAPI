package com.todo.consumer.service;

import java.io.IOException;

public interface TodoService {
	public Object getAllJsonData() throws IOException, InterruptedException;
	public Object getJsonData(String id) throws IOException, InterruptedException;

}

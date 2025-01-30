package com.todo.consumer.utils;

import org.springframework.stereotype.Component;

import com.todo.consumer.model.entity.Todo;
import com.todo.consumer.model.request.TodoRequest;

@Component
public class RequestUtils {

	public Todo setTodoModel(TodoRequest request) {
		return new Todo(Long.valueOf(request.getUserId()), request.getTitle(), request.isCompleted());

	}

}

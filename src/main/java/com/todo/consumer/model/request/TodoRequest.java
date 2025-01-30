package com.todo.consumer.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

	private int userId;
	private int id;
	private String title;
	private boolean completed;
}

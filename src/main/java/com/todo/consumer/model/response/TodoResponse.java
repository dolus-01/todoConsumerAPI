package com.todo.consumer.model.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoResponse {

	private int userId;
	private int id;
	private String title;
	private boolean completed;

}

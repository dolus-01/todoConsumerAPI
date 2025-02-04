package com.todo.consumer.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.consumer.ConsumerApplication;
import com.todo.consumer.model.entity.Todo;
import com.todo.consumer.model.request.TodoRequest;
import com.todo.consumer.model.response.TodoResponse;

@SpringBootTest(classes = { ConsumerApplication.class })
public class modelTest {

	@Test
	public void TestModel() {

		TodoRequest r = new TodoRequest();
		r.setCompleted(false);
		r.setId(1);
		r.setTitle("test");
		r.setUserId(1);

		r.getId();
		r.getTitle();
		r.getUserId();
		r.isCompleted();

		TodoResponse s = new TodoResponse();
		s.setCompleted(false);
		s.setId(1);
		s.setTitle("test");
		s.setUserId(1);

		s.getId();
		s.getTitle();
		s.getUserId();
		s.isCompleted();

		Todo todo = new Todo(1L, 1L, "test1", false);
		todo.setCompleted(false);
		todo.setId(3L);
		todo.setUserId(3L);
		todo.setTitle("test title 123");
		todo.getCompleted();
		todo.getId();
		todo.getTitle();
		todo.getUserId();

	}

}

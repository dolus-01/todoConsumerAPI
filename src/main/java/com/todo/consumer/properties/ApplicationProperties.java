package com.todo.consumer.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ApplicationProperties {

	@Value("${json.todo.url}")
	private String jsonTodoUrl;

	@Value("${json.todo.get.url}")
	private String jsonTodoGetUrl;

}

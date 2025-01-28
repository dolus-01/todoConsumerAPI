package com.todo.consumer.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Value("${json.todo.url}")
	private String jsonTodoUrl;

	@Value("${json.todo.get.url}")
	private String jsonTodoGetUrl;

	public String getJsonTodoGetUrl() {
		return jsonTodoGetUrl;
	}

	public void setJsonTodoGetUrl(String jsonTodoGetUrl) {
		this.jsonTodoGetUrl = jsonTodoGetUrl;
	}

	public String getJsonTodoUrl() {
		return jsonTodoUrl;
	}

	public void setJsonTodoUrl(String jsonTodoUrl) {
		this.jsonTodoUrl = jsonTodoUrl;
	}

}

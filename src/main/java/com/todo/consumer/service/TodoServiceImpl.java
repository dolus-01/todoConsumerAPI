package com.todo.consumer.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.consumer.properties.ApplicationProperties;
import com.todo.consumer.utils.ResponseUtils;

@Service
public class TodoServiceImpl implements TodoService {
	
	
	private HttpClient client;

	private ApplicationProperties props;

	private ResponseUtils responseUtils;

	@Autowired
	public TodoServiceImpl(ApplicationProperties props, ResponseUtils responseUtils) {
		client = HttpClient.newHttpClient();
		this.props = props;
		this.responseUtils = responseUtils;
	}

	public Object getAllJsonData() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(props.getJsonTodoUrl())).GET().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return responseUtils.getAllJsonDataResponse(response.body());
	}

	public Object getJsonData(String id) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(String.format(props.getJsonTodoGetUrl(), id))).GET()
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return responseUtils.getJsonDataResponse(response.body());
	}

}

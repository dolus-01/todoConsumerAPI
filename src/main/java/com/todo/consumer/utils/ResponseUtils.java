package com.todo.consumer.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.consumer.model.response.TodoResponse;

@Component
public class ResponseUtils {

	private ObjectMapper mapper = new ObjectMapper();

	public List<TodoResponse> getAllJsonDataResponse(String response) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(response, new TypeReference<List<TodoResponse>>() {
		});
	}

	public TodoResponse getJsonDataResponse(String response) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(response, new TypeReference<TodoResponse>() {
		});
	}

}

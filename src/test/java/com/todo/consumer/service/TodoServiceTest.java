package com.todo.consumer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.todo.consumer.ConsumerApplication;
import com.todo.consumer.model.response.TodoResponse;
import com.todo.consumer.properties.ApplicationProperties;
import com.todo.consumer.utils.ResponseUtils;

@SpringBootTest(classes = { ConsumerApplication.class })
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

	private TodoServiceImpl todoServiceImpl;

	@Autowired
	private ApplicationProperties props;

	@Autowired
	private ResponseUtils utils;

	private HttpResponse<String> mockResponse;

	@Spy
	private HttpClient client;

	private String jsonDataStringList = "[{\r\n" + "    \"userId\": 1,\r\n" + "    \"id\": 1,\r\n"
			+ "    \"title\": \"delectus aut autem\",\r\n" + "    \"completed\": false\r\n" + "  }]";

	private String jsonDataString = " {\r\n" + "    \"userId\": 1,\r\n" + "    \"id\": 2,\r\n"
			+ "    \"title\": \"quis ut nam facilis et officia qui\",\r\n" + "    \"completed\": false\r\n" + "  }";

	private String id;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {
		utils = new ResponseUtils();
		todoServiceImpl = new TodoServiceImpl(props, utils);
		id = "1";
		ReflectionTestUtils.setField(todoServiceImpl, "client", client);
		props.setJsonTodoUrl("https://jsonplaceholder.typicode.com/todos");
		props.setJsonTodoGetUrl("https://jsonplaceholder.typicode.com/todos/%s");
		mockResponse = mock(HttpResponse.class);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void TestShouldGetAllJsonData() throws IOException, InterruptedException {
		
		when(mockResponse.body()).thenReturn(jsonDataStringList);
	        
		when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandlers.ofString().getClass())))
	    .thenReturn(mockResponse);
		
		List<TodoResponse> response = (List<TodoResponse>) todoServiceImpl.getAllJsonData();
		assertEquals(response.get(0).getId(), 1);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void TestShouldGetJsonData() throws IOException, InterruptedException {
		
		when(mockResponse.body()).thenReturn(jsonDataString);
    
		when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandlers.ofString().getClass())))
	    .thenReturn(mockResponse);

		TodoResponse response = (TodoResponse) todoServiceImpl.getJsonData(id);
		assertEquals(response.getId(), 2);

	}

}

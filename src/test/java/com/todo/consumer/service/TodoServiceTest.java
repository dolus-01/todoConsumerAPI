package com.todo.consumer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.todo.consumer.model.entity.Todo;
import com.todo.consumer.model.request.TodoRequest;
import com.todo.consumer.model.response.TodoResponse;
import com.todo.consumer.properties.ApplicationProperties;
import com.todo.consumer.repository.TodoRepository;
import com.todo.consumer.utils.RequestUtils;
import com.todo.consumer.utils.ResponseUtils;

@SpringBootTest(classes = { ConsumerApplication.class })
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

	private TodoServiceImpl todoServiceImpl;

	@Autowired
	private ApplicationProperties props;

	@Autowired
	private TodoRepository repo;

	@Autowired
	private ResponseUtils utils;

	@Autowired
	private RequestUtils requestUtils;

	private HttpResponse<String> mockResponse;

	@Spy
	private HttpClient client;

	private String jsonDataStringList = "[{\r\n" + "    \"userId\": 1,\r\n" + "    \"id\": 1,\r\n"
			+ "    \"title\": \"delectus aut autem\",\r\n" + "    \"completed\": false\r\n" + "  }]";

	private String jsonDataString = " {\r\n" + "    \"userId\": 1,\r\n" + "    \"id\": 2,\r\n"
			+ "    \"title\": \"quis ut nam facilis et officia qui\",\r\n" + "    \"completed\": false\r\n" + "  }";

	private String id;
	private TodoRequest todo;

	private Long savedId;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {
		utils = new ResponseUtils();
		todoServiceImpl = new TodoServiceImpl(props, utils, requestUtils);
		id = "1";
		ReflectionTestUtils.setField(todoServiceImpl, "client", client);
		ReflectionTestUtils.setField(todoServiceImpl, "repo", repo);

		props.setJsonTodoUrl("https://test.com");
		props.setJsonTodoGetUrl("https://test.com/%s");
		mockResponse = mock(HttpResponse.class);

		todo = new TodoRequest();
		todo.setCompleted(false);
		todo.setId(3);
		todo.setTitle("test");
		todo.setUserId(3);

		Todo t = new Todo();
		t.setCompleted(true);
		t.setTitle("this is a sample Title");
		t.setUserId(1L);

		t = repo.save(t);
		savedId = t.getId();
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

	@Test
	public void TestShouldSaveNewData() {
		TodoRequest todo = new TodoRequest();
		todo.setCompleted(false);
		todo.setTitle("idol give me strength");
		todo.setUserId(555);
		Todo response = (Todo) todoServiceImpl.saveNewData(todo);
		assertEquals(response.getId(), 12);
	}

	@Test
	public void TestUpdateData() {
		TodoRequest todo = new TodoRequest();
		todo.setCompleted(false);
		todo.setTitle("idol give me strength");
		todo.setUserId(88888);
		Todo response = (Todo) todoServiceImpl.updateData(savedId.toString(), todo);
		assertEquals(response.getId(), 10);
	}

	@Test
	public void TestUpdateDataNoIdFound() {
		TodoRequest todo = new TodoRequest();
		todo.setCompleted(false);
		todo.setTitle("this is a failed test");
		todo.setUserId(88888);
		Todo response = (Todo) todoServiceImpl.updateData("6969", todo);
		assertEquals(response.getId(), null);
	}

	@Test
	public void TestDeleteData() {
		Object response = todoServiceImpl.deleteData(savedId.toString());
		assertEquals(response, "Data deleted succesfully");
	}

	@Test
	public void TestDeleteDataNoData() {

		Exception exception = assertThrows(NullPointerException.class, () -> {
			todoServiceImpl.deleteData("6969"); // Calling the method
		});

		assertEquals("Id not found in the database, deletion failed", exception.getMessage());

	}
	
	@Test
	public void TestShouldGetSavedData() {
		TodoRequest todo = new TodoRequest();
		todo.setCompleted(false);
		todo.setTitle("idol give me strength");
		todo.setUserId(555);
		Todo response = (Todo) todoServiceImpl.getSavedData(id);
		assertEquals(response.getId(), 1);
	}

}

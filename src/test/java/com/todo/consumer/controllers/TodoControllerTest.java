package com.todo.consumer.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.LinkedList;
import java.util.List;



import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.todo.consumer.model.response.TodoResponse;
import com.todo.consumer.service.TodoService;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
	@Autowired
	private MockMvc mvc;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@SuppressWarnings("removal")
	@MockBean
	private TodoService todoService;
	
	private List<TodoResponse> listResponse;
	
	private TodoResponse todoResponse;

	@BeforeEach
	public void setup() {
		todoResponse = new TodoResponse(); 
		todoResponse.setCompleted(false);
		todoResponse.setId(1);
		todoResponse.setTitle("testTItle");
		todoResponse.setUserId(1);
		List<TodoResponse> listResponse = new LinkedList<TodoResponse>();
		listResponse.add(todoResponse);
	}

	@Test
	public void TestGetAllData() throws Exception {
		given(todoService.getAllJsonData()).willReturn((Object)listResponse);
		mvc.perform(get("/json/data").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void TestGetData() throws Exception {
		given(todoService.getJsonData("1")).willReturn(todoResponse);
		mvc.perform(get("/json/data/{id}","1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}

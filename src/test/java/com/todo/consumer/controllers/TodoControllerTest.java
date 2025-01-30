package com.todo.consumer.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.todo.consumer.ConsumerApplication;
import com.todo.consumer.service.TodoService;

@SpringBootTest(classes = { ConsumerApplication.class })
@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

	private MockMvc mvc;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Mock
	private TodoService todoService;

	@InjectMocks
	private TodoController todoController;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(todoController).build();
	}

	@Test
	public void TestGetAllData() throws Exception {
		lenient().when(todoService.getAllJsonData()).thenReturn(new Object());
		mvc.perform(get("/dev/todo/json/data").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void TestGetData() throws Exception {
		lenient().when(todoService.getJsonData("1")).thenReturn(new Object());
//		given(todoService.getJsonData("1")).willReturn(new Object());
		mvc.perform(get(contextPath.concat("/json/data/{id}"), "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

}

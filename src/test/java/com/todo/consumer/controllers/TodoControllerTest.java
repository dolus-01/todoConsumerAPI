package com.todo.consumer.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.consumer.model.entity.Todo;
import com.todo.consumer.model.request.TodoRequest;
import com.todo.consumer.model.response.TodoResponse;
import com.todo.consumer.service.TodoService;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@SuppressWarnings("removal")
	@MockBean
	private TodoService todoService;

	private List<TodoResponse> listResponse;

	private TodoResponse todoResponse;

	private TodoRequest request;

	private String id;

	@BeforeEach
	public void setup() {
		todoResponse = new TodoResponse();
		todoResponse.setCompleted(false);
		todoResponse.setId(1);
		todoResponse.setTitle("testTItle");
		todoResponse.setUserId(1);
		List<TodoResponse> listResponse = new LinkedList<TodoResponse>();
		listResponse.add(todoResponse);
		request = new TodoRequest();
		request.setCompleted(false);
		request.setId(44);
		request.setTitle("this is my new title please forgive me ");
		request.setUserId(44);

		id = "1";
	}

	@Test
	public void TestGetAllData() throws Exception {
		given(todoService.getAllJsonData()).willReturn((Object) listResponse);
		mvc.perform(get("/json/data").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void TestGetData() throws Exception {
		given(todoService.getJsonData(id)).willReturn(todoResponse);
		mvc.perform(get("/json/data/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void TestSaveNewData() throws Exception {
		given(todoService.saveNewData(request)).willReturn(request);
		mvc.perform(
				post("/json/data").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void TestUpdateData() throws Exception {
		given(todoService.updateData(id, request)).willReturn(request);
		mvc.perform(
				post("/json/data").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void TestDeleteData() throws Exception {
		given(todoService.deleteData(id)).willReturn("Data deleted succesfully");
		mvc.perform(delete("/json/data/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void TestUpdateDataNoDataFound() throws Exception {
		given(todoService.updateData("6969", request)).willReturn(new Todo());
		mvc.perform(patch("/json/data/{id}", "6969").content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void TestgetSavedData() throws Exception {
		given(todoService.getSavedData(id)).willReturn(request);
		mvc.perform(get("/json/data/todo/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}

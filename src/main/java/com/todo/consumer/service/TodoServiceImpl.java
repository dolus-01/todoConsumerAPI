package com.todo.consumer.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.consumer.model.entity.Todo;
import com.todo.consumer.model.request.TodoRequest;
import com.todo.consumer.properties.ApplicationProperties;
import com.todo.consumer.repository.TodoRepository;
import com.todo.consumer.utils.RequestUtils;
import com.todo.consumer.utils.ResponseUtils;

@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

	private HttpClient client;

	private ApplicationProperties props;

	private ResponseUtils responseUtils;

	private RequestUtils requestUtils;

	@Autowired
	private TodoRepository repo;

	@Autowired
	public TodoServiceImpl(ApplicationProperties props, ResponseUtils responseUtils, RequestUtils requestUtil) {
		client = HttpClient.newHttpClient();
		this.props = props;
		this.responseUtils = responseUtils;
		this.requestUtils = requestUtil;
	}

	@Override
	public Object getAllJsonData() throws IOException, InterruptedException {
		log.info(String.format("Start method %s", "getAllJsonData"));
		HttpRequest request = HttpRequest.newBuilder(URI.create(props.getJsonTodoUrl())).GET().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return responseUtils.getAllJsonDataResponse(response.body());
	}

	@Override
	public Object getJsonData(String id) throws IOException, InterruptedException {
		log.info(String.format("Start method %s", "getJsonData"));

		HttpRequest request = HttpRequest.newBuilder(URI.create(String.format(props.getJsonTodoGetUrl(), id))).GET()
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return responseUtils.getJsonDataResponse(response.body());
	}

	@Transactional
	@Override
	public Object saveNewData(TodoRequest request) {
		log.info(String.format("Start method %s", "saveNewData"));

		Todo todo = requestUtils.setTodoModel(request);

		return repo.save(todo);
	}

	@Transactional
	@Override
	public Object updateData(String id, TodoRequest request) {
		log.info(String.format("Start method %s", "updateData"));
		Optional<Todo> todoData = repo.findById(Long.valueOf(id));

		Todo todo = todoData.orElse(null);

		if (todo != null) {
			log.info(String.format("Data updated succesfully"));
			todo = new Todo(todo.getId(), Long.valueOf(request.getUserId()), request.getTitle(),
					request.isCompleted());
			return repo.save(todo);
		} else {
			log.info(String.format("Id not found in the database"));
			return new Todo();
		}

	}

	@Transactional
	@Override
	public Object deleteData(String id) {
		log.info(String.format("Start method %s", "updateData"));
		Optional<Todo> todoData = repo.findById(Long.valueOf(id));

		Todo todo = todoData.orElse(null);

		if (todo == null) {
			log.info(String.format("Id not found in the database"));
			throw new NullPointerException("Id not found in the database, deletion failed");
		} else {
			repo.delete(todo);
			log.info(String.format("Data deleted succesfully"));
			return "Data deleted succesfully";
		}

	}

	@Override
	public Object getSavedData(String id) {
		log.info(String.format("Start method %s", "getSavedData"));

		Optional<Todo> todoData = repo.findById(Long.valueOf(id));
		return todoData.orElse(new Todo());
	}

}

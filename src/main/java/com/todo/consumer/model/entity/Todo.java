package com.todo.consumer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq_generator")
	@SequenceGenerator(name = "todo_seq_generator", sequenceName = "TODO_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "title")
	private String title;

	@Column(name = "completed")
	private Boolean completed;

	public Todo(Long userId, String title, Boolean completed) {
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}

}

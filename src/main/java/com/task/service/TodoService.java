package com.task.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.task.model.Todo;

@Service
public interface TodoService {
	public List<Todo> retrieveTodos(String user);
	public void addTodo(String name, String desc, Date targetDate,
            boolean isDone);
	public void deleteTodo(int id);
	public void updateTodo(Todo todo);
	public Todo retrieveTodo(int id);
}

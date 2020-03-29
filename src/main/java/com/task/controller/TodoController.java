package com.task.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.task.model.Todo;
import com.task.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/todo-list", method = RequestMethod.GET)
	public String todoList(ModelMap model) {
		model.put("todos", todoService.retrieveTodos(getLoggedInUserName()));
		return "todo-list";
	}

	@RequestMapping(value = "/todo-add", method = RequestMethod.GET)
	public String addTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(), "", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/todo-add", method = RequestMethod.POST)
	public String addTodoTask(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		todoService.addTodo(getLoggedInUserName(), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/todo-list";
	}

	@RequestMapping(value = "/todo-delete", method = RequestMethod.GET)
	public String deleteTodoTask(ModelMap model, @RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/todo-list";
	}

	@RequestMapping(value = "/todo-update", method = RequestMethod.GET)
	public String updateTodoTaskPage(ModelMap model, @RequestParam int id) {
		Todo todo = todoService.retrieveTodo(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/todo-update", method = RequestMethod.POST)
	public String updateTodoTask(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser(getLoggedInUserName());
		todoService.updateTodo(todo);
		return "redirect:/todo-list";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
}

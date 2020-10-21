package com.wilsonbluong.javabeltexamretake.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wilsonbluong.javabeltexamretake.models.Task;
import com.wilsonbluong.javabeltexamretake.models.User;
import com.wilsonbluong.javabeltexamretake.services.TaskService;
import com.wilsonbluong.javabeltexamretake.services.UserService;

@Controller
public class TasksController {
	
	private final UserService userService;
	private final TaskService taskService;
	
	public TasksController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}
	
	// render create page
	@GetMapping("/tasks/new")
	public String newTask(@ModelAttribute("task") Task task, Model model, HttpSession session) {
		// if current user is in session then proceed, if not redirect to login page
		if (session.getAttribute("userId") != null) {
			// find a list of all users
			List<User> users = userService.allUsers();
			model.addAttribute("users", users);

			// get current user login info

			// when get user id from session, don't forget to cast data type to Long
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("currentUser", u);
			return "new.jsp";
		} else {
			System.out.println("You have not logged in");
			return "redirect:/";
		}
	}
	
	// create task
	@PostMapping("/tasks/new")
	public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		
		if (result.hasErrors()) {

			// passing list of users back to dropdown menu when errors validation is activated.
			List<User> users = userService.allUsers();
			model.addAttribute("users", users);
			return "new.jsp";
		} else {		
			Task newTask = taskService.saveTask(task);
			// add creator(current login user) to newly created task
			newTask.setCreator(u);
			// save change by calling updateTask()
			taskService.saveTask(newTask);
			
			return "redirect:/tasks";
		}
	}
		
	// render task id page
	@GetMapping("/tasks/{task_id}")
	public String showTask(@PathVariable("task_id") Long taskId, Model model) {
		model.addAttribute("task", taskService.findTaskId(taskId));
		return ("taskId.jsp");
	}
	
	// delete task
	@PostMapping("/tasks/{task_id}/delete")
	private String deleteTask(@PathVariable("task_id") Long id) {
		taskService.deleteTask(id);
		return "redirect:/tasks";
	}
	
	// render edit task page
	@GetMapping("/tasks/{task_id}/edit")
	public String displayEditPage(Model model, @ModelAttribute("task") Task task, @PathVariable("task_id") Long taskId,
			HttpSession session) {

		// get current login user id from session
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);

		// get a task by id
		Task editingTask = taskService.findTaskId(taskId);

		if (u.getId() == editingTask.getCreator().getId()) {

			// List all users
			List<User> users = userService.allUsers();

			// Getting a task's creator, model pass info back to JSP
			model.addAttribute("creator", editingTask.getCreator());
			model.addAttribute("task", editingTask);
			model.addAttribute("users", users);
			model.addAttribute("id", editingTask.getId());
			return "edit.jsp";
		} 
		else {
			return "redirect:/tasks";
		}
	}
		
	// update task
	@PostMapping("/tasks/{task_id}/edit")	
	public String updateTask(Model model, @Valid @ModelAttribute("task") Task task, BindingResult result,
			@PathVariable("task_id") Long taskId, HttpSession session) {

		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		if (result.hasErrors()) {
			// passing list of users back to drop-down menu when errors validation is activated.
			List<User> users = userService.allUsers();
			model.addAttribute("users", users);
			return "edit.jsp";
		} else {
			
			Task editTask = taskService.saveTask(task);
			// add creator(current login user) to edit task
			editTask.setCreator(u);
			// save() change by calling updateTask()
			taskService.saveTask(editTask);
			
			// you only need to save task when passing creater_id through hidden input
			// taskService.saveTask(task);
			return "redirect:/tasks";
		}
	}
}

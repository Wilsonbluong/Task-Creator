package com.wilsonbluong.javabeltexamretake.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wilsonbluong.javabeltexamretake.models.User;
import com.wilsonbluong.javabeltexamretake.services.TaskService;
import com.wilsonbluong.javabeltexamretake.services.UserService;
import com.wilsonbluong.javabeltexamretake.validators.UserValidator;

@Controller
public class UsersController {
	
	private final UserService userService;
	private final TaskService taskService;
	private final UserValidator userValidator;
	    
    public UsersController(UserService userService,
    		TaskService taskService, UserValidator userValidator) {
    	this.userService = userService;
    	this.taskService = taskService;
    	this.userValidator = userValidator;
    }
    
    // renders registration and login form
    @GetMapping("/")
    public String registerForm(@ModelAttribute("user") User user, Model model, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "regLogPage.jsp";
    	}
    	else {
    		return "redirect:/tasks";
    	} 
    }
    
    // register new user
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
        // if result has errors, return the registration page
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		return ("regLogPage.jsp");
    	}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		User u = userService.registerUser(user);
    		session.setAttribute("userId", u.getId());
    		return ("redirect:/tasks");
    	}
    }
    
    // authenticates user to allow access to events page
    @PostMapping("/login")
    public String loginUser(
    		@RequestParam("email") String email,
    		@RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return ("redirect:/tasks");
    	}
        // else, add error messages and return the login page
    	else {
    		session.setAttribute("error", "Invalid Credentials. Please try again.");
    		return "redirect:/";
    	}
    }
    
    // renders home page with user info
    @GetMapping("/tasks")
    public String home(HttpSession session, Model model) {
    	// if user not in session return redirect to login page
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	// get user from session, save them in the model and return the home page
    	else {
        	Long userId = (Long) session.getAttribute("userId");
        	User user = userService.findUserById(userId);
        	model.addAttribute("user", user);
        	
        	// get all tasks to display on home page
        	model.addAttribute("tasks", taskService.allTasks());
    		return "main.jsp";
    	}
    }
    
    // logs user out
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/";
    }

}

package com.wilsonbluong.javabeltexamretake.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wilsonbluong.javabeltexamretake.models.Task;
import com.wilsonbluong.javabeltexamretake.repositories.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> allTasks() {
		return taskRepository.findAll();
	}
	
	public Task saveTask(Task t) {
		return taskRepository.save(t);
	}
	
	public Task findTaskId(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
			if(optionalTask.isPresent()) {
				return optionalTask.get();
			}
			else {
				return null;
			}
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	

}

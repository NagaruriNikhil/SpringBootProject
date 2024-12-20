package com.learnings.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.springboot.document.Task;
import com.learnings.springboot.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	public Task saveTask(Task task)
	{
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return repository.save(task);
	}
	
	public List<Task> findTasks(){
		return repository.findAll();
	}
	
	public Task getTask(String id) {
		return repository.findById(id).get();
	}
	
	public Task updateTask(Task task) {
		//get existing object from db by taskid
		//then set new value from the request
		Task existingTask = repository.findById(task.getTaskId()).get();
		existingTask.setDescription(task.getDescription());
		existingTask.setPriority(task.getPriority());
		existingTask.setStoryPoint(task.getStoryPoint());
		
		return repository.save(existingTask);
		
	}
	
	public String deleteTask(String taskId)
	{
		 repository.deleteById(taskId);
		 return taskId +" task is deleted";
		
	}
	
	public List<Task> getTaskByAssigneeAndPriority(String assignee, String priority)
	{
//		return repository.findByAssigneeAndPriority(assignee, priority);
		return repository.findTaskWithAssigneeAndPriority(assignee, priority);

	}
}

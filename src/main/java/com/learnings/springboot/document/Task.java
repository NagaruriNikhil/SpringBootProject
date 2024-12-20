package com.learnings.springboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//collection is same as Table 
@Document(collection= "Tasks")  //@Entity, @Table(name="")
@Data

public class Task {
	@Id
	private String taskId;
	private String description;
	private String priority;
	private String assignee;
	private int storyPoint;

	public Task(String description, String priority, String assignee, int storyPoint) {
		this.description = description;
		this.priority = priority;
		this.assignee = assignee;
		this.storyPoint = storyPoint;
	}

	public String getTaskId() {
		return taskId;
	}

	public Task(String taskId, String description, String priority, String assignee, int storyPoint) {
		this.taskId = taskId;
		this.description = description;
		this.priority = priority;
		this.assignee = assignee;
		this.storyPoint = storyPoint;
	}

	public Task() {
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public int getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}


}

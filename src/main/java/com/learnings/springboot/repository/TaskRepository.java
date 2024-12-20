package com.learnings.springboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.learnings.springboot.document.Task;

public interface TaskRepository extends MongoRepository<Task, String>{

	//with jpa name signature
	List<Task> findByAssigneeAndPriority(String assignee, String priority);
	
	
	//with any method name
	//this is in the mongoquery to filter based on the fields and if we want to get only specified fields in the result 
	//then pass fields in the query
	
	@Query(value ="{assignee : ?0 , priority: ?1}", fields= "{'description': 1, 'storyPoint': 2}")
	List<Task> findTaskWithAssigneeAndPriority(String assignee, String priority);


}

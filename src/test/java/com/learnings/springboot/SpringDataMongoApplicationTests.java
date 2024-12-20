package com.learnings.springboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ObjectInputFilter.Status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnings.springboot.controller.TaskController;
import com.learnings.springboot.document.Task;

@SpringBootTest
@AutoConfigureMockMvc // will load mvc related web application context
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // to avoid @beforeall class to be non static

class SpringDataMongoApplicationTests {

	@Autowired
	private TaskController taskController;


	@Test
	public void contextLoads() {
		assertNotNull(taskController);
	}
	// complete infra of MVC will be ready with mockmvc
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(TaskController.class).build();
	}

	@Test
	public void addNewTasktest() throws Exception {
		//url -> /api/tasks
		Task task= new Task("Implement security","p1","nikhil1", 3);
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/tasks")
						.content(convertObjAsString(task)) //converts to json string from object
						.contentType("application/json")
						.accept("application/json"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.taskId").exists());

	}

	private String convertObjAsString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

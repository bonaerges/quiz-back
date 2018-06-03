package com.bonaerges.quizback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	 @Autowired
	    private WebApplicationContext context;

	    private MockMvc mockMvc;

	    @Test
	    public void getCourseList() throws Exception {
	        mockMvc.perform(get("/course"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8")) 
	            .andExpect(content().encoding("UTF-8"))
	            .andExpect(jsonPath("$[0].id").exists())
	            .andExpect(jsonPath("$[0].name").exists());
	        
	    }
}

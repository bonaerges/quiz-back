package com.dbg.quizback.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.dbg.quizback.dto.UserDTO;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class CourseDAOTest {

	 @InjectMocks
	 private UserDAO userDAO;
	 
	 @Test
	    public void findAll() {
		 
	    }
}

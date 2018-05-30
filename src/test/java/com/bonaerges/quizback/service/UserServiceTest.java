package com.bonaerges.quizback.service;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonaerges.quizback.model.User;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class UserServiceTest {

	 @Autowired
	    private UserService userService;
	 
	 @Test
	    public void findAll() {
	   
	        Set<User> testName = userService.findAll(PageRequest.of(1, 10));
	       
	    }
}

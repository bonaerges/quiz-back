package com.bonaerges.quizback.controller;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bonaerges.quizback.model.User;
import com.bonaerges.quizback.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;
 
   
   // @Test
//    public void test_get_all_success() throws Exception {
//        List<User> users=userService.findAll(PageRequest.of(1, 10));
//        User mockUser=new User();
//        mockUser.setEmail("test1@quizback.com");
//        mockUser.setName("Test1");
//        
//    	User createUser=userService.create(mockUser);
//    	
//    	
//    }
}

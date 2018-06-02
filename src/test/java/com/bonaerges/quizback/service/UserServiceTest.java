package com.bonaerges.quizback.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.model.User;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	    private UserService userService=new UserServiceImpl();
	 
	 @Mock
	 private User user;
		
	 @Mock
	 private UserDAO userDAO;
		
	 @Before
	    public void setUp() throws Exception {
			user= new User();
			user.setEmail("test1@quizback.com");
			user.setId(1);
			user.setName("TEST1");
			user.setSurname("SURNAMETEST");
			userDAO.save(user);			
	    }
	 
	 @Test
		public void testMockCreation() {
			assertNotNull(userService);
			assertNotNull(userDAO);
			assertNotNull(user);
		}
	 
	 @Test
	    public void findAll() {
	    }
	 
	 @Test
	  public void testFindUserByEmail() {	 
	      String email = "test1@quizback.com";
	      Optional<User> userDAOObj = userDAO.findByEmail(email);	 
	      Mockito.when(userDAO.findByEmail(email)).thenReturn(userDAOObj);
	      Optional<User> userServiceObj=userService.findByEmail(email);
	      assertNotNull(userServiceObj);
	      Assert.assertEquals(userDAOObj.get().getEmail(), userServiceObj.get().getEmail());
	  }
}

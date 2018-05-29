package com.dbg.quizback.component.mapper.user;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbg.quizback.component.mapper.user.UserMapper;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.model.User;

@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {
	@InjectMocks
	UserMapper userMapper=new UserMapperImpl();
	
	@Mock
	DozerBeanMapper dozer;
	
	@Test
	public void testDtoToModelOK() {
		final UserDTO userDTO=new UserDTO();
		userDTO.setName("PEPE");
		final User user=new User();
		user.setName("PEPE");
//		Mockito.when(dozer.map(userDTO, user).thenReturn(user);
//		final UserToCheck = dozer.map(source, destinationClass)
	}
}

package com.dbg.quizback.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.UserDAO;
import com.dbg.quizback.model.User;

@Service
public class UserServiceImpl implements UserService, InitializingBean {

	private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO dao;

	@Override
	public void test() {
		final User u = new User();
	
		
		u.setEmail("asd@g.com");
		u.setName("Pepe 1");
		u.setPassword("pepe123");
		dao.save(u);
		Optional<User> user = dao.findOneByNameOrderByIdUserDesc("Pepe 1");
		
		
		logger.debug(user.isPresent() ? " User Pepe 1 founded!! " : "User Pepe 1 did not found");
		
		user= dao.findOneByNameOrderByIdUserDesc("Pepe 2");
		logger.debug(user.isPresent() ? " User Pepe 2 founded!! ":  "User Pepe2 did not found");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		test();
	}

}

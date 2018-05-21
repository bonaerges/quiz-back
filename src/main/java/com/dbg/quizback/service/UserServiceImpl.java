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
	UserDAO userDAO;
 
	@Override
	public User create(User t) {		
		return userDAO.save(t);
	}

	@Override
	public void update(User t) {
		userDAO.save(t);
		
	}

	@Override
	public void add(Integer i) {
		//TODO
	}

	@Override
	public void delete(User t) {
		userDAO.delete(t);
		
	}
	@Override
	public Optional<User> findById(Integer id){		
		return userDAO.findById(id);		
	}
	
	public Optional<User> findOneByNameOrderByIdUserDesc(String name){
		return userDAO.findOneByNameOrderByIdUserDesc(name);
		
	}
	
	@Override
	public void test() {
		final User u = new User();
	
		
		u.setEmail("asd@g.com");
		u.setName("Pepe 1");
		u.setPassword("pepe123");
		userDAO.save(u);
		Optional<User> user = userDAO.findOneByNameOrderByIdUserDesc("Pepe 1");
		
		
		logger.debug(user.isPresent() ? " User Pepe 1 founded!! " : "User Pepe 1 did not found");
		
		user= userDAO.findOneByNameOrderByIdUserDesc("Pepe 2");
		logger.debug(user.isPresent() ? " User Pepe 2 founded!! ":  "User Pepe2 did not found");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		test();
	}

}

package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.UserDAO;
import com.dbg.quizback.model.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO userDAO;
 
	@Override
	public User create(User t) {
		User userObject=userDAO.save(t);
		logger.info(" User create successfully " + t.toString());
		return userObject;
	}

	@Override
	public void update(User t) {
		userDAO.save(t);
		logger.info(" User update successfully " + t.toString());
		
	}
	@Override
	public void delete(User t) {
		userDAO.delete(t);
		logger.info(" User delete successfully " + t.toString());
		
	}
	@Override
	public Optional<User> findById(Integer id){	
		Optional <User> userObject=userDAO.findById(id);		
		logger.info(" User findById successfully " + userObject.toString());
		return userObject;
	}
	
	@Override
	public Set<User> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return userDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<User> findOneByNameOrderByIdUserDesc(String name){
		Optional <User> userObject=userDAO.findOneByNameOrderByIdUserDesc(name);
		userObject.ifPresent(u ->logger.info("User findOneByNameOrderByIdUserDesc "  + u.toString()));
		return userObject;
		
	}
	
}

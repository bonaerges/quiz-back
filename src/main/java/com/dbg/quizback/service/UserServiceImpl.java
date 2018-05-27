package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dbg.quizback.dao.CourseDAO;
import com.dbg.quizback.dao.UserDAO;
import com.dbg.quizback.exception.DuplicatedException;
import com.dbg.quizback.exception.NotFoundException;
import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO userDAO;
 
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	DozerBeanMapper dozerBean;
	
	@Override
	@ExceptionHandler({ ConstraintViolationException.class })
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
	
	public Optional<User> findOneByNameOrderByIdDesc(String name){
		Optional <User> userObject=userDAO.findOneByNameOrderByIdDesc(name);
		userObject.ifPresent(u ->logger.info("User findOneByNameOrderByIdDesc "  + u.toString()));
		return userObject;
		
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userDAO.findOneByEmail(email);
	}
	
	@Override
	/**
	 * 
	 */
	public void addUserCourse(User userModel, Integer idCourse) throws NotFoundException, DuplicatedException {
		Optional<Course> courseObject=courseDAO.findById(idCourse);
		boolean belongsCourse=false;
		if (courseObject.isPresent()) {
			belongsCourse=isUserOnCourse(idCourse,userModel.getEmail());
			if (belongsCourse)
				logger.warn(" User "+ userModel.getId()+" already was included into course "+ idCourse  );
			else
			{
				userModel.getCourse().add(courseObject.get());
				userDAO.save(userModel);
				courseDAO.save(courseObject.get());
					logger.info(" Add User "+ userModel.getId()+" to course "+ idCourse + " successfully " );
			}
		}

	}
	
	
	/***********VALIDATIONS METHODS**************************************************/
	/**
	 * 
	 * @param idCourse
	 * @param email
	 * @return
	 * @throws NotFoundException
	 * @throws DuplicatedException
	 */
	private boolean isUserOnCourse(final Integer idCourse, final String email) throws NotFoundException, DuplicatedException {
		Optional<User> user = userDAO.findOneByEmail(email);
        boolean isOnCourse=true;
        if (user.isPresent())
        {
        	Optional<Course> courseUser = user.get().getCourse()
		            .stream()
		            .filter(course -> course.getId() == idCourse)
		            .findFirst();
			if (courseUser.isPresent())
				throw new DuplicatedException ("Course with ID: '" + idCourse + "' does not contain user  with mail: '" + email+ "'");
        	else  {
        		isOnCourse=false;
        	}
			}
		else throw new NotFoundException ("User with mail: '" + email+ "' Not FOUND");
        
		return isOnCourse;
        
    }

	@Override
	public Optional<Course> findCourseByEmail(String email,Integer idCourse) throws NotFoundException {
		Optional<User> user = userDAO.findOneByEmail(email);
		  if (user.isPresent())
	        {
	        	Optional<Course> courseUser = user.get().getCourse()
			            .stream()
			            .filter(course -> course.getId() == idCourse)
			            .findFirst();
	        	return courseUser;
	        }
		  else throw new NotFoundException ("User with mail: '" + email+ "' Not FOUND");
	}
	

}


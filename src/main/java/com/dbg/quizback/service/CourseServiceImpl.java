package com.dbg.quizback.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbg.quizback.dao.CourseDAO;
import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;

@Service
public class CourseServiceImpl implements CourseService {

	private static final Logger logger= LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	CourseDAO courseDAO;
 
	@Override
	public Course create(Course t) {
		Course courseObject=courseDAO.save(t);
		logger.info(" Course create successfully " + t.toString());
		return courseObject;
	}

	@Override
	public void update(Course t) {
		courseDAO.save(t);
		logger.info(" Course update successfully " + t.toString());
		
	}
	@Override
	public void delete(Course t) {
		courseDAO.delete(t);
		logger.info(" Course delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Course> findById(Integer id){	
		Optional <Course> courseObject=courseDAO.findById(id);		
		logger.info(" Course findById successfully " + courseObject.toString());
		return courseObject;
	}
	
	@Override
	public Set<Course> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return courseDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Course> findOneByDescription(String name){
		Optional <Course> courseObject=courseDAO.findOneByDescription(name);
		courseObject.ifPresent(c ->logger.info("Course findOneByDescription "  + c.toString()));
		return courseObject;
		
	}
	public Optional<Course> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Course> courseObject=courseDAO.findOneByDescriptionOrderByIdDesc(name);
		courseObject.ifPresent(t ->logger.info("Course findOneByDescriptionOrderByIdDesc "  + t.toString()));
		return courseObject;
		
	}

	@Override
	public Set<User> findAllUserById(Integer idCourse) {
		Set <User> usersByCourse=new HashSet<User>();
		Optional <Course> courseObject=courseDAO.findById(idCourse);	
		if (courseObject.isPresent()) {
			usersByCourse=courseObject.get().getUser();
		}
		return usersByCourse;
	}


	
}

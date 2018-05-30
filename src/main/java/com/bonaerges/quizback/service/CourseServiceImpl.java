package com.bonaerges.quizback.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.CourseDAO;
import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	UserDAO userDAO;
 
	@Override
	public Course create(Course t) {
		Course courseObject=courseDAO.save(t);
		log.info(" Course create successfully " + t.toString());
		return courseObject;
	}

	@Override
	public void update(Course t) {
		courseDAO.save(t);
		log.info(" Course update successfully " + t.toString());
		
	}
	@Override
	public void delete(Course t) {
		courseDAO.delete(t);
		log.info(" Course delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Course> findById(Integer id){	
		Optional <Course> courseObject=courseDAO.findById(id);		
		log.info(" Course findById successfully " + courseObject.toString());
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
		courseObject.ifPresent(c ->log.info("Course findOneByDescription "  + c.toString()));
		return courseObject;
		
	}
	public Optional<Course> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Course> courseObject=courseDAO.findOneByDescriptionOrderByIdDesc(name);
		courseObject.ifPresent(t ->log.info("Course findOneByDescriptionOrderByIdDesc "  + t.toString()));
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

	@Override
	public void addUserToCourse(Integer idCourse, Integer idUser) {
		Optional <Course> courseObject=courseDAO.findById(idCourse);	
		Optional <User> userObject=userDAO.findById(idUser);	
		if (courseObject.isPresent()) {
			userObject.ifPresent(u ->{
			courseObject.get().getUser().add(u);
			
			 courseDAO.save(courseObject.get());
			});
		
		}
		
	}

	@Override
	public void deleteUserFromCourse(Integer idCourse, Integer idUser) {
		Optional <Course> courseObject=courseDAO.findById(idCourse);	
		Optional <User> userObject=userDAO.findById(idUser);	
		if (courseObject.isPresent()) {
			userObject.ifPresent(u -> {
			 Optional<User> optUser =courseObject.get().getUser().stream().collect(Collectors.toList()).stream()
					    .filter(s -> idUser.equals(s.getId()))
					    .findFirst();
			 if (optUser.isPresent())
			 {
				 courseObject.get().getUser().remove(optUser.get()); 
			 }
			});
			 courseDAO.save(courseObject.get());
		
		
		}
		
		
	}
	
	public boolean belongsUserToCourse(Integer idUser, Integer idCourse)  {
        boolean belongsToCourse=false;
        Optional <Course> courseObject=courseDAO.findById(idCourse);	
		Optional <User> userObject=userDAO.findById(idUser);	
		if (userObject.isPresent() ) {
			belongsToCourse=courseObject.get().getUser().stream().collect(Collectors.toList()).stream()
					    .filter(s -> idUser.equals(s.getId()))
					    .findFirst().isPresent();
				
        }
		//else throw new NotFoundException ("User with ID: "+ idUser+ " does not belong to Course with ID:" + idCourse );
	
		return belongsToCourse;
        
    }	


	
}

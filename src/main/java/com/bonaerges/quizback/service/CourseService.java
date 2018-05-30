package com.bonaerges.quizback.service;

import java.util.Set;

import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.User;

public interface CourseService extends AbstractCrossService<Course,Integer> {
	
	public Set<User> findAllUserById(Integer idCourse);
	
	public void addUserToCourse(Integer idCourse, Integer idUser);
	
	public void deleteUserFromCourse(Integer idCourse, Integer idUser);

	public boolean belongsUserToCourse(Integer idUser, Integer idCourse)  ;
	
}

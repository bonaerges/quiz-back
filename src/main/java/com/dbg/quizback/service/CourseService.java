package com.dbg.quizback.service;

import java.util.Set;

import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;

public interface CourseService extends AbstractCrossService<Course,Integer> {
	
	public Set<User> findAllUserById(Integer idCourse);
	
	public void addUserToCourse(Integer idCourse, Integer idUser);
	
	public void deleteUserFromCourse(Integer idCourse, Integer idUser);

	
}

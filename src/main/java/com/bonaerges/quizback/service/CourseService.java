package com.bonaerges.quizback.service;

import java.util.List;

import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.User;

public interface CourseService extends AbstractCrossService<Course,Integer> {
	
	public List<User> findAllUserById(Integer idCourse);
	
	public void addUserToCourse(Integer idCourse, Integer idUser);
	
	public void deleteUserFromCourse(Integer idCourse, Integer idUser);

	public boolean belongsUserToCourse(Integer idUser, Integer idCourse)  ;

	public void deleteQuestionnarieCourse(Integer idCourse, Integer idQuestionnaire);
	
	public void deleteUserCouse(Integer idCourse, Integer idUser);
	
	public void addQuestionnarieCourse(Integer idCourse, Integer idQuestionnaire) throws NotFoundException;
}

package com.dbg.quizback.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Course;

@Repository
public interface CourseDAO extends AbstractCrossDAO<Course> {

	Optional<Course> findOneByDescription(String name);
	
	Set <Course> findAllByDescription(String name);
	
	Optional<Course> findOneByDescriptionOrderByIdDesc(String name);

}

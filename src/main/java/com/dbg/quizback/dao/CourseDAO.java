package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;

@Repository
public interface CourseDAO extends PagingAndSortingRepository<Course, Integer> {

	Optional<Course> findOneByDescription(String name);
	
	Optional<Course> findAllByDescription(String name);

}

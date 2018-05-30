package com.bonaerges.quizback.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Course;

@Repository
public interface CourseDAO extends PagingAndSortingRepository<Course, Integer> {

	Optional<Course> findOneByDescription(String name);
	
	Set <Course> findAllByDescription(String name);
	
	Optional<Course> findOneByDescriptionOrderByIdDesc(String name);

	

}

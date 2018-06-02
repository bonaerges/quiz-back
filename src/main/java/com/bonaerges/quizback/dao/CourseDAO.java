package com.bonaerges.quizback.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Course;

@Repository
public interface CourseDAO extends PagingAndSortingRepository<Course, Integer> {

	Optional<Course> findOneByDescription(String name);
	
	List<Course> findAllByDescription(String name);
	
	Optional<Course> findOneByDescriptionOrderByIdDesc(String name);

	

}

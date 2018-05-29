package com.dbg.quizback.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Integer>{

	Optional<User> findOneByNameOrderByIdDesc(String name);

	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	Optional<User> findByEmail(@Param("email")String email);
	
	@Query("SELECT u FROM Course c,User u WHERE c.id = :idCourse  ")
	Set<User> findUsersByCourse(@Param("idCourse")Integer idCourse);
	
	long countByName(String name);
	 
}

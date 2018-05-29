package com.dbg.quizback.service;

import java.util.Optional;

import com.dbg.quizback.exception.DuplicatedException;
import com.dbg.quizback.exception.NotFoundException;
import com.dbg.quizback.model.User;

public interface UserService extends AbstractCrossService<User,Integer> {

	
	void addUserCourse(User userModel, Integer idCourse) throws NotFoundException, DuplicatedException;
	Optional<User> findByEmail(String email);

}

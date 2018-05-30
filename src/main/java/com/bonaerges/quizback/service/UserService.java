package com.bonaerges.quizback.service;

import java.util.Optional;

import com.bonaerges.quizback.exception.DuplicatedException;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.User;

public interface UserService extends AbstractCrossService<User,Integer> {

	
	void addUserCourse(User userModel, Integer idCourse) throws NotFoundException, DuplicatedException;
	Optional<User> findByEmail(String email);

}

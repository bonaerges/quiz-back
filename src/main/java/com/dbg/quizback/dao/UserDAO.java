package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.User;

@Repository
public interface UserDAO extends AbstractCrossDAO<User>{

	Optional<User> findOneByNameOrderByIdDesc(String name);

}

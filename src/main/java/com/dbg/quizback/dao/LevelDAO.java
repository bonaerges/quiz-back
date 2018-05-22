package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Level;

@Repository
public interface LevelDAO extends AbstractCrossDAO<Level> {

	Optional<Level> findOneByNameOrderById(String name);

}

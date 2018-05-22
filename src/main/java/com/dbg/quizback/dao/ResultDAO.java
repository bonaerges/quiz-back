package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Result;

@Repository
public interface ResultDAO extends AbstractCrossDAO<Result> {

	Optional<Result> findOneByNameOrderByIdDesc(String name);

}

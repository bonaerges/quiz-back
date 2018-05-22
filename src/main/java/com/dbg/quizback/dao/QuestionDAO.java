package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Question;

@Repository
public interface QuestionDAO extends AbstractCrossDAO<Question> {

	Optional<Question> findOneByDescriptionOrderByIdDesc(String description);

}

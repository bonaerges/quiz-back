package com.dbg.quizback.service;

import java.util.Optional;

import com.dbg.quizback.model.Question;

public interface QuestionService extends AbstractCrossService<Question,Integer> {

	Optional<Question> findByDescription(String name);
	
}

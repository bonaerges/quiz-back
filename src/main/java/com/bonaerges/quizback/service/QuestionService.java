package com.bonaerges.quizback.service;

import java.util.Optional;

import com.bonaerges.quizback.model.Question;

public interface QuestionService extends AbstractCrossService<Question,Integer> {

	Optional<Question> findByDescription(String name);
	
}

package com.bonaerges.quizback.service;

import com.bonaerges.quizback.model.Answer;

public interface AnswerService extends AbstractCrossService<Answer,Integer> {

	Iterable<Answer> findAll();
	void addAnswerQuestion(Answer t, Integer idQuestion);
	void deleteAnswerQuestion(Integer idA, Integer id) ;
	
}

package com.dbg.quizback.service;

import com.dbg.quizback.model.Answer;

public interface AnswerService extends AbstractCrossService<Answer,Integer> {

	Iterable<Answer> findAll();
	void addAnswerQuestion(Answer t, Integer idQuestion);
	
}

package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;

import com.dbg.quizback.model.QuestionUserAnswerId;
import com.dbg.quizback.model.QuestionnaireUserAnswer;

public interface QuestionnaireUserAnswerService  {

	QuestionnaireUserAnswer create(QuestionUserAnswerId t);

	void update(QuestionUserAnswerId t);

	//void delete(QuestionUserAnswerId t);

	Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerId id);

	Set<QuestionnaireUserAnswer> findAll();
	
	}

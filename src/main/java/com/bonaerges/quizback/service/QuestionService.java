package com.bonaerges.quizback.service;

import java.util.Optional;

import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;

public interface QuestionService extends AbstractCrossService<Question,Integer> {

	Optional<Question> findByDescription(String name);

	void addAnswerQuestion(Answer answerModel, Integer id);

	void deleteAnswerQuestion(Integer idA, Integer id) ;

	void addAnswerQuestion(Integer idA, Integer id) ;
	
}

package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.Result;

public interface QuestionService extends AbstractCrossService<Question,Integer> {

	Optional<Question> findByDescription(String name);

	void addAnswerQuestion(Answer answerModel, Integer id);

	void deleteAnswerQuestion(Integer idA, Integer id) ;

	void addAnswerQuestion(Integer idA, Integer id) ;

	Optional<Questionnaire> findQuestionnaire(Integer idQuestionnaire);
	
	public List<Question> findAllByQuestionnaire(Integer idQuest);
	
}

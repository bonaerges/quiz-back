package com.bonaerges.quizback.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bonaerges.quizback.model.QuestionUserAnswerPK;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;

public interface QuestionnaireUserAnswerService extends AbstractCrossService<QuestionnaireUserAnswer,QuestionUserAnswerPK> {

	void validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer);

	void calculateResultUserQuestionAnswers(Integer idQuestionnaire, Integer idUser);

	List<QuestionnaireUserAnswer> findUsersAnswerByQuestionnarie(@Param("idQ")Integer idQ,@Param("idU")Integer IdU);
	
	List<QuestionnaireUserAnswer> findByIdQuestionnaire(@Param("idQ")Integer idQ);
	
	List<QuestionnaireUserAnswer> findByIdQuestionnaireAndUser(@Param("idQ")Integer idQ,@Param("idU")Integer idU);
}

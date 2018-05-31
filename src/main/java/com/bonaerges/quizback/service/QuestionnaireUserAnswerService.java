package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;

import com.bonaerges.quizback.model.QuestionUserAnswerId;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;

public interface QuestionnaireUserAnswerService extends AbstractCrossService<QuestionnaireUserAnswer,QuestionUserAnswerId> {

	void validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer);

	Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerId id);
	
	
}

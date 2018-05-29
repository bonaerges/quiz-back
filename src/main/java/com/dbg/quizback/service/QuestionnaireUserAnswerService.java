package com.dbg.quizback.service;

import java.util.List;
import java.util.Optional;

import com.dbg.quizback.model.QuestionUserAnswerId;
import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.model.QuestionnaireUserAnswer;
import com.dbg.quizback.model.Result;

public interface QuestionnaireUserAnswerService extends AbstractCrossService<QuestionnaireUserAnswer,QuestionUserAnswerId> {

	Result validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer);

	Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerId id);
	
	
}

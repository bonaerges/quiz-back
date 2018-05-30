package com.bonaerges.quizback.service;

import java.util.List;

import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.Result;

public interface QuestionnaireService extends AbstractCrossService<Questionnaire,Integer> {
	Result validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer);
	
}

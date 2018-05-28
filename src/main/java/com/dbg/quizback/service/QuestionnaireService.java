package com.dbg.quizback.service;

import java.util.List;

import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.model.QuestionnaireUserAnswer;
import com.dbg.quizback.model.Result;

public interface QuestionnaireService extends AbstractCrossService<Questionnaire,Integer> {
	Result validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer);
	
}

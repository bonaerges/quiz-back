package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.QuestionUserAnswerId;
import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.model.QuestionnaireUserAnswer;

@Repository
public interface QuestionnaireUserAnswerDAO extends PagingAndSortingRepository<QuestionnaireUserAnswer, QuestionUserAnswerId> {



}

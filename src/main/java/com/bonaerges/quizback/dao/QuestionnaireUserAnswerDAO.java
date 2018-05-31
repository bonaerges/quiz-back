package com.bonaerges.quizback.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.QuestionUserAnswerId;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;

@Repository
public interface QuestionnaireUserAnswerDAO extends PagingAndSortingRepository<QuestionnaireUserAnswer, QuestionUserAnswerId> {



}

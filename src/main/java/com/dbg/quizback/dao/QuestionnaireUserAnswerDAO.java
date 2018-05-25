package com.dbg.quizback.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.QuestionUserAnswerId;
import com.dbg.quizback.model.QuestionnaireUserAnswer;

@Repository
public interface QuestionnaireUserAnswerDAO extends PagingAndSortingRepository<QuestionnaireUserAnswer, QuestionUserAnswerId> {

	QuestionnaireUserAnswer save(QuestionUserAnswerId t);

	//void delete(QuestionUserAnswerId t);

	public Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerId id) ;
	
	public Set<QuestionnaireUserAnswer> findAll() ;

}

package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Answer;

@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer> {

	Optional<Answer> findOneByDescriptionOrderByIdDesc(String description);
	
	Optional<Answer> findAllByDescription(String description);

}

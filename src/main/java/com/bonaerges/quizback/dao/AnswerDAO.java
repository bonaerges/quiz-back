package com.bonaerges.quizback.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Answer;

@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer> {

	Optional<Answer> findOneByDescriptionOrderByIdDesc(String description);
	
	Set <Answer>findAllByDescription(String description);

}

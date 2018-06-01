package com.bonaerges.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.User;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer> {

	Optional<Question> findOneByDescriptionOrderByIdDesc(String description);

	Optional<Question> findByDescription(String name);
	
	
}

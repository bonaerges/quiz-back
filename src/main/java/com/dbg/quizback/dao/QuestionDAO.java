package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Question;
import com.dbg.quizback.model.User;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer> {

	Optional<Question> findOneByDescriptionOrderByIdDesc(String description);
	
}

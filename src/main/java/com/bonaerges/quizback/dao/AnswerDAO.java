package com.bonaerges.quizback.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Answer;

@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer> {

	Optional<Answer> findOneByDescriptionOrderByIdDesc(String description);
	
	@Query("SELECT a FROM Answer a WHERE LOWER(a.description) = LOWER(:description)")
	List<Answer> findAllByDescription(@Param("description")String description);

}

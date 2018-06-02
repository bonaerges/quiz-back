package com.bonaerges.quizback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Result;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result,Integer > {

	@Query("SELECT u FROM Result u WHERE u.questionary.id = :idQuest ")
	List<Result> findAllByQuestionnarie(@Param("idQuest")Integer idQuest);
	
}

package com.bonaerges.quizback.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Question;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer> {

	Optional<Question> findOneByDescriptionOrderByIdDesc(String description);

	Optional<Question> findByDescription(String name);
	
	//@Query("SELECT q FROM Questionnaire As q JOIN q.question AS q WHERE q.question= qz.questionnaire AND q.questionnaire = :idQuest")
	List<Question> findAllByQuestionnaire(Integer idQuest);
	
}

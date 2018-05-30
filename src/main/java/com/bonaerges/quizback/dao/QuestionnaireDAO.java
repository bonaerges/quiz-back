package com.bonaerges.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Questionnaire;

@Repository
public interface QuestionnaireDAO extends PagingAndSortingRepository<Questionnaire, Integer> {

	Optional<Questionnaire> findOneByDescriptionOrderByIdDesc(String desc);

}

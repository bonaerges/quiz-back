package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Questionnaire;

@Repository
public interface QuestionnaireDAO extends AbstractCrossDAO<Questionnaire> {

	Optional<Questionnaire> findOneByDescriptionOrderByIdDesc(String desc);

}

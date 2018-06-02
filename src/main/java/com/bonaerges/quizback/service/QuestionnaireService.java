package com.bonaerges.quizback.service;

import java.util.Optional;

import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;

public interface QuestionnaireService extends AbstractCrossService<Questionnaire,Integer> {

	public Optional<Course> getCourse (Integer idCourse) throws NotFoundException;
	public void addQuestionnarieCourse(Integer idCourse, Integer idQuestionnaire) throws NotFoundException;
}

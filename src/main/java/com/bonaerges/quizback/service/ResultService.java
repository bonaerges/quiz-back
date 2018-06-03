package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.Result;

public interface ResultService extends AbstractCrossService<Result,Integer> {
	
	public Optional<Course> getCourse (Integer idCourse) throws NotFoundException;
	
	public Optional<Questionnaire> getQuestionnaire (Integer idQuestionnaire) ;

	List<QuestionnaireUserAnswer> findUsersAnswerByQuestionnarie(@Param("idQ")Integer idQ,@Param("idU")Integer IdU);
	
	List<Result> findAllByQuestionnarie(Integer idQuest);
	
}

package com.bonaerges.quizback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.QuestionUserAnswerId;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.User;

@Repository
public interface QuestionnaireUserAnswerDAO extends PagingAndSortingRepository<QuestionnaireUserAnswer, QuestionUserAnswerId> {


	@Query("SELECT u FROM Course c,User u WHERE c.id = :idCourse  ")
	List<User> findUsersByCourse(@Param("idCourse")Integer idCourse);
	
	@Query("SELECT qUA from QuestionnaireUserAnswer qUA WHERE qUA.questionUserAnswerId.idQuestionnaire=:idQ AND UA.questionUserAnswerId.idUser=:idU")
	List<QuestionnaireUserAnswer> findUsersAnswerByQuestionnarie(@Param("idQ")Integer idQ,@Param("idU")Integer IdU);
 
}

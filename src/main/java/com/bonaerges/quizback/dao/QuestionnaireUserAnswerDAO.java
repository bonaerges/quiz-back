package com.bonaerges.quizback.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.QuestionUserAnswerPK;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.User;

@Repository
public interface QuestionnaireUserAnswerDAO extends PagingAndSortingRepository<QuestionnaireUserAnswer, QuestionUserAnswerPK> {


	@Query("SELECT u FROM Course c,User u WHERE c.id = :idCourse  ")
	List<User> findUsersByCourse(@Param("idCourse")Integer idCourse);
	
	
	@Query("SELECT qUA from QuestionnaireUserAnswer qUA")
	List<QuestionnaireUserAnswer> findUsersAnswerByQuestionnarie(@Param("idQ")Integer idQ,@Param("idU")Integer idU);
 
	Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerPK quAPK);
	
	@Query("SELECT e FROM QuestionnaireUserAnswer e WHERE e.id.questionnaireId = :idQ")
	List<QuestionnaireUserAnswer> findByIdQuestionnaire(@Param("idQ")Integer idQ);
	
	@Query("SELECT e FROM QuestionnaireUserAnswer e WHERE e.id.questionnaireId = :idQ AND  e.id.userId = :idU")
	List<QuestionnaireUserAnswer> findByIdQuestionnaireAndUser(@Param("idQ")Integer idQ,@Param("idU")Integer idU);
	
	List<QuestionnaireUserAnswer> findAll();
	//List<QuestionnaireUserAnswer> findAllByIdQuestionnaireAndIdUser(Integer idQuestionnaire,Integer idUser );
}

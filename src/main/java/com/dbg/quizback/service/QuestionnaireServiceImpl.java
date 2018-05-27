package com.dbg.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.QuestionDAO;
import com.dbg.quizback.dao.QuestionnaireDAO;
import com.dbg.quizback.dao.ResultDAO;
import com.dbg.quizback.dao.UserDAO;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.model.QuestionUserAnswerId;
import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.model.QuestionnaireUserAnswer;
import com.dbg.quizback.model.Result;
import com.dbg.quizback.model.User;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	private static final Logger logger= LoggerFactory.getLogger(QuestionnaireServiceImpl.class);
	
	@Autowired
	QuestionnaireDAO questionnaireDAO;
	
	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public Questionnaire create(Questionnaire t) {
		Questionnaire questionnaireObject=questionnaireDAO.save(t);
		logger.info(" Questionnaire create successfully " + t.toString());
		return questionnaireObject;
	}

	@Override
	public void update(Questionnaire t) {
		questionnaireDAO.save(t);
		logger.info(" Questionnaire update successfully " + t.toString());
		
	}
	@Override
	public void delete(Questionnaire t) {
		questionnaireDAO.delete(t);
		logger.info(" Questionnaire delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Questionnaire> findById(Integer id){	
		Optional <Questionnaire> questionnaireObject=questionnaireDAO.findById(id);	
		logger.info(" Questionnaire findById successfully " + questionnaireObject.toString());
		return questionnaireObject;
	}
	
	@Override
	public Set<Questionnaire> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return questionnaireDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Questionnaire> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Questionnaire> questionnaireObject=questionnaireDAO.findOneByDescriptionOrderByIdDesc(name);
		questionnaireObject.ifPresent(q ->logger.info("Questionnaire findOneByDescriptionOrderByIdDesc "  + q.toString()));
		return questionnaireObject;
		
	}
	

	@Override
	public Result validateAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer) {
	
		//Get all questions from questionnaire
		List<Question> questionsQuiz=questionnaire.getQuestion();
		 
		Result resultUser=new Result();
		double averageNote;
	
		
		//From the filled quiz select the correct answer and compare with answer provide by user
		questionsQuiz.forEach(questionFilledQ -> {
			Answer correctAnswer=questionFilledQ.getCorrectAnswer();
			//get answer provide by user
			resultsQuestionAnswer.forEach(answerUser ->  {
				int countCorrect=0;
				int countIncorrect=0;
				int toalQuestions=0;
				Optional<User> userAnswer=userDAO.findById(answerUser.getUser().getId());
				if (answerUser.getQuestion().getId() ==	questionFilledQ.getId()) {
					if (answerUser.getSelectedAnswer().getId() == correctAnswer.getId()) {
						//find user that answer questionnaire for save into result	and update answer OK and KO					
						
						if (userAnswer.isPresent()) {
							countCorrect++;
						}
					}
					else countIncorrect++;
					if (userAnswer.isPresent()) {
						resultUser.setTotalAnswerOK(countCorrect);
						resultUser.setTotalAnswerKO(countIncorrect);
						resultUser.setTotalQuestions(toalQuestions+1);
						resultUser.setUser(userAnswer.get());
					}					
				}
				});
			} );
		

		return resultUser;
	}

	
}

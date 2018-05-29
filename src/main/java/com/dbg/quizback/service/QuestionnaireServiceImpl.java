package com.dbg.quizback.service;

import static org.assertj.core.api.Assertions.filter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.AnswerDAO;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService {


	@Autowired
	QuestionnaireDAO questionnaireDAO;
	
	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Override
	public Questionnaire create(Questionnaire t) {
		Questionnaire questionnaireObject=questionnaireDAO.save(t);
		log.info(" Questionnaire create successfully " + t.toString());
		return questionnaireObject;
	}

	@Override
	public void update(Questionnaire t) {
		questionnaireDAO.save(t);
		log.info(" Questionnaire update successfully " + t.toString());
		
	}
	@Override
	public void delete(Questionnaire t) {
		//Delete questions linked to questionnaire
		t.getQuestion().forEach(question ->questionDAO.deleteById(question.getId()));
		questionnaireDAO.delete(t);
		log.info(" Questionnaire delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Questionnaire> findById(Integer id){	
		Optional <Questionnaire> questionnaireObject=questionnaireDAO.findById(id);	
		log.info(" Questionnaire findById successfully " + questionnaireObject.toString());
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
		questionnaireObject.ifPresent(q ->log.info("Questionnaire findOneByDescriptionOrderByIdDesc "  + q.toString()));
		return questionnaireObject;
		
	}
	

	@Override
	public Result validateQuestionAnswers(Questionnaire questionnaire, List<QuestionnaireUserAnswer> resultsQuestionAnswer) {
	
		//Get all questions from questionnaire
		List<Question> questionsQuiz=questionnaire.getQuestion();
		 
		Result resultUser=new Result();
		double averageNote;
	
		
		//From the filled quiz , for each answer given to question, select the correct answer and compare with answer provide by user
		questionsQuiz.forEach(questionFilledQ -> {
			Answer correctAnswer=questionFilledQ.getCorrectAnswer();
			//get answer provide by user
			resultsQuestionAnswer.forEach(answerUser ->  {
				
				int countCorrect=resultUser.getTotalAnswerOK();
				int countIncorrect=resultUser.getTotalAnswerKO();
				int toalQuestions=resultUser.getTotalQuestions();
				boolean answerSelected=false;
				Optional<User> userAnswer=userDAO.findById(answerUser.getUser().getId());
				if (userAnswer.isPresent()) {
					//Compare if question answer from questionnaire is the same question answered
					if (answerUser.getQuestion().getId() ==	questionFilledQ.getId()) {
						//Check if answer provide for the question is the correctAnswer to sumarize error or succeed
						boolean answeredCorrect= Optional.ofNullable(answerUser)
					    .map(aUser -> aUser.getAnswer())
						.filter(aUser->aUser.getId() == correctAnswer.getId()).isPresent();
						if (answeredCorrect) {
							//find user that answer questionnaire for save into result	and update answer OK and KO					
								countCorrect++;
								answerSelected=true;
						}
						else {
							countIncorrect++;
						}
					}
								
				}
				if (answerSelected) {
					resultUser.setTotalAnswerOK(countCorrect);
					resultUser.setTotalAnswerKO(countIncorrect);
					resultUser.setTotalQuestions(toalQuestions+1);
					resultUser.setUser(userAnswer.get());
				}	
				else {
					resultUser.setTotalAnswerOK(0);
					resultUser.setTotalAnswerKO(countIncorrect);
					resultUser.setTotalQuestions(toalQuestions+1);
					resultUser.setUser(userAnswer.get());
				}
				
				});
			} );
		

		return resultUser;
	}

	
}

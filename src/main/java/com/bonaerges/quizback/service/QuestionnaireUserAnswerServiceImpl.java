package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.QuestionnaireDAO;
import com.bonaerges.quizback.dao.QuestionnaireUserAnswerDAO;
import com.bonaerges.quizback.dao.ResultDAO;
import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.QuestionUserAnswerId;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.Result;
import com.bonaerges.quizback.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireUserAnswerServiceImpl implements QuestionnaireUserAnswerService {

	@Autowired
	QuestionnaireDAO questionnaireDAO;
	
	@Autowired
	QuestionnaireUserAnswerDAO questionnaireuserAnswerDAO;
	
	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	@Override
	public QuestionnaireUserAnswer create(QuestionnaireUserAnswer t) {
		QuestionnaireUserAnswer qUAObj= questionnaireuserAnswerDAO.save(t);
		log.info(" Questionnaire User Answer create successfully " + t.toString());
		return qUAObj;
	}

	@Override
	public void update(QuestionnaireUserAnswer t) {
		questionnaireuserAnswerDAO.save(t);
		log.info(" Questionnaire User Answer save successfully " + t.toString());		
	}

	@Override
	public void delete(QuestionnaireUserAnswer t) {
		questionnaireuserAnswerDAO.delete(t);
		log.info(" Questionnaire User Answer delete successfully " + t.toString());		
		
	}

	@Override
	public Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerId id) {
		Optional <QuestionnaireUserAnswer> qUAOb=questionnaireuserAnswerDAO.findById(id);	
		log.info(" Questionnaire findById successfully " + qUAOb.toString());
		return qUAOb;
	}

	@Override
	public List<QuestionnaireUserAnswer> findAll(Pageable p) {
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return questionnaireuserAnswerDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());	
	}

	@Override
	public void validateQuestionAnswers(Questionnaire questionnaire,
			List<QuestionnaireUserAnswer> resultsQuestionAnswer) {
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
					Optional<User> userAnswer=userDAO.findById(answerUser.getId().getIdUser());
					if (userAnswer.isPresent()) {
						//Compare if question answer from questionnaire is correct one for question answered
						if (answerUser.getId().getIdQuestion() == questionFilledQ.getId()) {
							//Check if answer provide for the question is the correctAnswer to sumarize error or succeed
							boolean answeredCorrect= Optional.ofNullable(answerUser)
							.filter(aUser->aUser.getId().getIdAnswer() == correctAnswer.getId()).isPresent();
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
			
			resultUser.setQuestionary(questionnaire);
			resultDAO.save(resultUser);
		
		}

	
	}





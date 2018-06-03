package com.bonaerges.quizback.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.QuestionnaireUserAnswerDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.QuestionUserAnswerPK;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.Result;
import com.bonaerges.quizback.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireUserAnswerServiceImpl implements QuestionnaireUserAnswerService {

	@Autowired
	QuestionnaireService questionnaireService;

	@Autowired
	QuestionnaireUserAnswerDAO questionnaireuserAnswerDAO;

	@Autowired
	ResultService resultService;

	@Autowired
	UserService userService;

	@Override
	public QuestionnaireUserAnswer create(QuestionnaireUserAnswer t) {
		// Create result adn then linked to answer
		final Result res = new Result();
		QuestionnaireUserAnswer qUAObj = new QuestionnaireUserAnswer();
		Optional<User> userAnswer = userService.findById(t.getId().getUserId());
		Optional<Questionnaire> quiz = questionnaireService.findById(t.getId().getQuestionnaireId());
		
		if (userAnswer.isPresent() && quiz.isPresent()) {
			qUAObj.setId(t.getId());
			qUAObj.setDate(t.getDate());
//			res.setUser(userAnswer.get());
//			res.setDate(new Date());
//			res.setQuestionary(quiz.get());
//			res.setAverageNote(0);
//			res.setTotalAnswerKO(0);
//			res.setTotalAnswerOK(0);
//			res.setTotalQuestions(1);
//			res.getQuestionnaireUserAnswer().add(e);
//			Result resCreate1= resultService.create(res);
//			Optional <Result> resCreate2=resultService.findById(resCreate1.getId());
			res.setId(1);
			qUAObj.setResult(Collections.singletonList(res));
			
			qUAObj = questionnaireuserAnswerDAO.save(qUAObj);
			log.info(" Questionnaire User Answer create successfully " + t.toString());
		} else
			log.error(" Resul User Answer does not saved user or questionary not found ");
		return qUAObj;
	}

	@Override
	public void update(QuestionnaireUserAnswer t) {
		QuestionnaireUserAnswer qUAObj = new QuestionnaireUserAnswer();
		qUAObj.setId(t.getId());
		qUAObj.setDate(t.getDate());
		t.setDate(new Date());
		questionnaireuserAnswerDAO.save(t);

		log.info(" Questionnaire User Answer save successfully " + t.toString());
	}

	@Override
	public void delete(QuestionnaireUserAnswer t) {
		questionnaireuserAnswerDAO.delete(t);
		log.info(" Questionnaire User Answer delete successfully " + t.toString());

	}

	public Optional<QuestionnaireUserAnswer> findById(QuestionUserAnswerPK id) {
		Optional<QuestionnaireUserAnswer> qUAOb = questionnaireuserAnswerDAO.findById(id);
		log.info(" Questionnaire findById successfully " + qUAOb.toString());
		return qUAOb;
	}

	@Override
	public List<QuestionnaireUserAnswer> findAll(Pageable p) {
		int page = p.getPageNumber();
		int size = p.getPageSize();
		return questionnaireuserAnswerDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}

	@Override
	public void validateQuestionAnswers(Questionnaire questionnaire,
			List<QuestionnaireUserAnswer> resultsQuestionAnswer) {
		// Get all questions from questionnaire
		List<Question> questionsQuiz = questionnaire.getQuestion();

		Result resultUser = new Result();
		double averageNote;

		// From the filled quiz , for each answer given to question, select the correct
		// answer and compare with answer provide by user
		questionsQuiz.forEach(questionFilledQ -> {
			Answer correctAnswer = questionFilledQ.getCorrectAnswer();
			// get answer provide by user
			resultsQuestionAnswer.forEach(answerUser -> {

				int countCorrect = resultUser.getTotalAnswerOK();
				int countIncorrect = resultUser.getTotalAnswerKO();
				int toalQuestions = resultUser.getTotalQuestions();
				boolean answerSelected = false;

				Optional<User> userAnswer = userService.findById(answerUser.getId().getUserId());
				if (userAnswer.isPresent()) {
					// Compare if question answer from questionnaire is correct one for question
					// answered
					if (answerUser.getId().getQuestionId() == questionFilledQ.getId()) {
						// Check if answer provide for the question is the correctAnswer to sumarize
						// error or succeed
						boolean answeredCorrect = Optional.ofNullable(answerUser)
								.filter(aUser -> aUser.getId().getAnswerId() == correctAnswer.getId()).isPresent();
						if (answeredCorrect) {
							// find user that answer questionnaire for save into result and update answer OK
							// and KO
							countCorrect++;
							answerSelected = true;
						} else {
							countIncorrect++;
						}
					}

				}
				if (answerSelected) {
					resultUser.setTotalAnswerOK(countCorrect);
					resultUser.setTotalAnswerKO(countIncorrect);
					resultUser.setTotalQuestions(toalQuestions + 1);
					resultUser.setUser(userAnswer.get());
				} else {
					resultUser.setTotalAnswerOK(0);
					resultUser.setTotalAnswerKO(countIncorrect);
					resultUser.setTotalQuestions(toalQuestions + 1);
					resultUser.setUser(userAnswer.get());
				}

			});

		});

		resultUser.setQuestionary(questionnaire);
		resultService.update(resultUser);

	}

	@Override
	public void calculateResultUserQuestionAnswers(Integer idQuestionnaire,Integer idUser) {
		//Filter form result user idUser from idQuestionarrie to calculate         
		List<QuestionnaireUserAnswer>  answerUser=questionnaireuserAnswerDAO.findUsersAnswerByQuestionnarie(idQuestionnaire, idUser);
         }
	
	public List<QuestionnaireUserAnswer> findUsersAnswerByQuestionnarie(@Param("idQ") Integer idQ,
			@Param("idU") Integer idU) {
		return questionnaireuserAnswerDAO.findUsersAnswerByQuestionnarie(idQ, idU);
	}

	public List<QuestionnaireUserAnswer> findByIdQuestionnaire(@Param("idQ")Integer idQ){
		return questionnaireuserAnswerDAO.findByIdQuestionnaire(idQ);
	}

	public List<QuestionnaireUserAnswer> findByIdQuestionnaireAndUser(@Param("idQ")Integer idQ,@Param("idU")Integer idU){
		return questionnaireuserAnswerDAO.findByIdQuestionnaireAndUser(idQ, idU);
	}
}

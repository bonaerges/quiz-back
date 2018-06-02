package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.QuestionDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.Questionnaire;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	AnswerService answerService;
	
	@Autowired
	QuestionnaireService questionnaireService;

	@Override
	public Question create(Question t) {
		Question questionObject = questionDAO.save(t);
		log.info(" Question create successfully " + t.toString());
		return questionObject;
	}
	

	@Override
	public void update(Question t) {
		questionDAO.save(t);
		log.info(" Question update successfully " + t.toString());

	}

	@Override
	public void delete(Question t) {
		// Delete answer linked to question
		t.getAnswer().forEach(answer -> answerService.delete(answer));
		questionDAO.delete(t);
		log.info(" Question-Asnwer delete successfully " + t.toString());

	}

	@Override
	public Optional<Question> findById(Integer id) {
		Optional<Question> questionObject = questionDAO.findById(id);
		log.info(" Question findById successfully " + questionObject.toString());
		return questionObject;
	}

	@Override
	public List<Question> findAll(Pageable p) {
		int page = p.getPageNumber();
		int size = p.getPageSize();
		return (List<Question>) questionDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}

	public Optional<Question> findOneByDescriptionOrderByIdDesc(String name) {
		Optional<Question> questionObject = questionDAO.findOneByDescriptionOrderByIdDesc(name);
		questionObject.ifPresent(q -> log.info("Question findOneByDescriptionOrderByIdDesc " + q.toString()));
		return questionObject;

	}

	// TO modify the correct answer map to a question in case user need to change
	// response
	public void updateSelectedAnswer(Question question, Answer answer) {
		Optional<Question> questionObject = questionDAO.findById(question.getId());
		if (questionObject.isPresent()) {
			questionObject.get().setCorrectAnswer(answer);
			questionDAO.save(questionObject.get());
		}
	}

	@Override
	public Optional<Question> findByDescription(String name) {
		Optional<Question> questionObject = questionDAO.findByDescription(name);
		log.info(" Question findByDescription successfully " + questionObject.toString());
		return questionObject;
	}

	@Override
	// iT IS ALLOWED TO CREATE ANSWER IF THIS DOES NOT EXIST LINKED TO QUESTION
	// EXISTING
	public void addAnswerQuestion(Answer answerModel, Integer id) {
		Optional<Question> question = questionDAO.findById(id);
		final Answer answerCreate;
		if (question.isPresent()) {
			// add answers linked to current question
			answerModel.setQuestion(question.get());
			answerCreate = answerService.create(answerModel);
			question.get().getAnswer().add(answerCreate);
			// questionDAO.save(question.get());
			log.info("Succesfully add question " + id + " and answers linked to question");
		}
		// else throw new NotFoundException("Question id "+ id + " Not found");

	}

	@Override
	// Delete answer linked to question
	public void deleteAnswerQuestion(Integer idA, Integer id) {
		Optional<Question> question = questionDAO.findById(id);
		if (question.isPresent()) {
			// delete all answers linked to current question
			boolean existMatchAnswer = question.get().getAnswer().stream()
					.anyMatch(answerLink -> answerLink.getId() == idA);
			if (existMatchAnswer) {
				log.error("Answer id " + idA + " already linked to question id " + id);
			} else {
				Optional<Answer> answer = answerService.findById(idA);
				question.get().getAnswer().remove(answer.get());
				// questionDAO.save(question.get());
				log.info("Succesfully delete answer " + idA + " linked to question id " + id);
			}
		}

	}

	@Override
	// Add answer linked to question
	public void addAnswerQuestion(Integer idA, Integer id) {
		Optional<Question> question = questionDAO.findById(id);
		if (question.isPresent()) {
			// delete all answers linked to current question
			boolean existMatchAnswer = question.get().getAnswer().stream()
					.anyMatch(answerLink -> answerLink.getId() == idA);
			if (existMatchAnswer) {
				log.error("Answer id " + idA + " already linked to question id " + id);
			} else {
				Optional<Answer> answer = answerService.findById(idA);
				question.get().getAnswer().add(answer.get());
				questionDAO.save(question.get());
				log.info("Succesfully add answer " + idA + " linked to question id " + id);
			}
		}
	}
	@Override
	public Optional<Questionnaire> findQuestionnaire(Integer idQuestionnaire) {
		Optional<Questionnaire> questionObject = questionnaireService.findById(idQuestionnaire);
		log.info(" Questionnaire findById successfully " + questionObject.toString());
		return questionObject;
	}

}

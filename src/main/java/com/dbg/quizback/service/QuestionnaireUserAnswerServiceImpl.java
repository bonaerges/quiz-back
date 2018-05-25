package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.QuestionnaireUserAnswerDAO;
import com.dbg.quizback.model.QuestionnaireUserAnswer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionnaireUserAnswerServiceImpl implements QuestionnaireUserAnswerService {

	@Autowired
	QuestionnaireUserAnswerDAO questionnaireUserAnswerDAO;
	
	@Override
	public QuestionnaireUserAnswer create(QuestionnaireUserAnswer t) {
		QuestionnaireUserAnswer questionnaireUserAnswerObject=questionnaireUserAnswerDAO.save(t);
		log.info(" Questionnaire User Answer create successfully " + t.toString());
		return questionnaireUserAnswerObject;
	}

	@Override
	public void update(QuestionnaireUserAnswer t) {
		questionnaireUserAnswerDAO.save(t);
		log.info("  Questionnaire User Answer  update successfully " + t.toString());
		
	}

	@Override
	public void delete(QuestionnaireUserAnswer t) {
		questionnaireUserAnswerDAO.delete(t);
		log.info(" Questionnaire User Answer delete successfully " + t.toString());
		
	}

	@Override
	public Optional<QuestionnaireUserAnswer> findById(Integer id) {
		Optional <QuestionnaireUserAnswer> questionnaireUserAnswerObject=questionnaireUserAnswerDAO.findById(id);		
		log.info(" Questionnaire User Answer findById successfully " + questionnaireUserAnswerObject.toString());
		return questionnaireUserAnswerObject;
	}

	@Override
	public Set<QuestionnaireUserAnswer> findAll(Pageable p) {
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return questionnaireUserAnswerDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
	}

}

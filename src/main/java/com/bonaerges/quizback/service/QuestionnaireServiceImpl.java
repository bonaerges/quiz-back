package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.QuestionDAO;
import com.bonaerges.quizback.dao.QuestionnaireDAO;
import com.bonaerges.quizback.dao.ResultDAO;
import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.model.Result;
import com.bonaerges.quizback.model.User;

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
	public List<Questionnaire> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return questionnaireDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());		
	}
	
	public Optional<Questionnaire> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Questionnaire> questionnaireObject=questionnaireDAO.findOneByDescriptionOrderByIdDesc(name);
		questionnaireObject.ifPresent(q ->log.info("Questionnaire findOneByDescriptionOrderByIdDesc "  + q.toString()));
		return questionnaireObject;
		
	}
	


	
}

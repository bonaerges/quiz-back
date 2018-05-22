package com.dbg.quizback.service;

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
import com.dbg.quizback.model.Questionnaire;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	private static final Logger logger= LoggerFactory.getLogger(QuestionnaireServiceImpl.class);
	
	@Autowired
	QuestionnaireDAO questionnaireDAO;
 
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

	
}

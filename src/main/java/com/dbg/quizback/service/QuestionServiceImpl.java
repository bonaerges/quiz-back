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
import com.dbg.quizback.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger logger= LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Autowired
	QuestionDAO questionDAO;
 
	@Override
	public Question create(Question t) {
		Question questionObject=questionDAO.save(t);
		logger.info(" Question create successfully " + t.toString());
		return questionObject;
	}

	@Override
	public void update(Question t) {
		questionDAO.save(t);
		logger.info(" Question update successfully " + t.toString());
		
	}
	@Override
	public void delete(Question t) {
		questionDAO.delete(t);
		logger.info(" Question delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Question> findById(Integer id){	
		Optional <Question> questionObject=questionDAO.findById(id);		
		logger.info(" Question findById successfully " + questionObject.toString());
		return questionObject;
	}
	
	@Override
	public Set<Question> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return questionDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Question> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Question> questionObject=questionDAO.findOneByDescriptionOrderByIdDesc(name);
		questionObject.ifPresent(q ->logger.info("Question findOneByDescriptionOrderByIdDesc "  + q.toString()));
		return questionObject;
		
	}

	
}

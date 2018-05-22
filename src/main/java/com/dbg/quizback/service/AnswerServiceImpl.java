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

import com.dbg.quizback.dao.AnswerDAO;
import com.dbg.quizback.model.Answer;

@Service
public class AnswerServiceImpl implements AnswerService {

	private static final Logger logger= LoggerFactory.getLogger(AnswerServiceImpl.class);
	
	@Autowired
	AnswerDAO answerDAO;
 
	@Override
	public Answer create(Answer t) {
		Answer answerObject=answerDAO.save(t);
		logger.info(" Answer create successfully " + t.toString());
		return answerObject;
	}

	@Override
	public void update(Answer t) {
		answerDAO.save(t);
		logger.info(" Answer update successfully " + t.toString());
		
	}
	@Override
	public void delete(Answer t) {
		answerDAO.delete(t);
		logger.info(" Answer delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Answer> findById(Integer id){	
		Optional <Answer> answerObject=answerDAO.findById(id);		
		logger.info(" Answer findById successfully " + answerObject.toString());
		return answerObject;
	}
	
	@Override
	public Set<Answer> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return answerDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Answer> findOneByDescriptionOrderByIdDesc(String name){
		Optional <Answer> answerObject=answerDAO.findOneByDescriptionOrderByIdDesc(name);
		answerObject.ifPresent(a ->logger.info("Answer findOneByDescriptionOrderByIdDesc "  + a.toString()));
		return answerObject;
		
	}

	
}

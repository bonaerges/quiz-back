package com.dbg.quizback.service;

import java.util.Objects;
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
import com.dbg.quizback.dao.QuestionDAO;
import com.dbg.quizback.exception.NotFoundException;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;

@Service
public class AnswerServiceImpl implements AnswerService {

	private static final Logger logger= LoggerFactory.getLogger(AnswerServiceImpl.class);
	
	@Autowired
	AnswerDAO answerDAO;

	@Autowired
	QuestionDAO questionDAO;
	
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
	//Link answer to previous existing question
	public void addAnswerQuestion(Answer t,Integer idQuestion) {
		Optional<Question> question=questionDAO.findById(idQuestion);
		Answer answerObject=t;
		
		question.ifPresent(questionObj -> {
			answerObject.setQuestion(questionObj);
			answerDAO.save(answerObject);});			
		logger.info(" Add Answer to question successfully " + answerObject.toString());
		
	}
	
	//Link answer to previous existing question
	public void updatedAnswerQuestion(Answer t,Integer idAnswer,Integer idQuestion) throws NotFoundException {
	Optional<Question> question=questionDAO.findById(idQuestion);
	Answer answerObject=t;
	if (question.isPresent()) {
	 if (isAnswerMapToQuestion(idQuestion,idAnswer)) {
		   answerObject.setId(idAnswer);
			answerDAO.save(answerObject);
			logger.info(" Add Answer " + idAnswer + " to question " + idQuestion + " successfully " + answerObject.toString());
	 	}
	}	
	}
	@Override
	public void delete(Answer t) {
		answerDAO.delete(t);
		logger.info(" Answer delete successfully " + t.toString());
		
	}
	
	//Delete and answer mapped to an existing question
	 public void deleteAnswerQuestion(Integer idQuestion, Integer idAnswer) throws NotFoundException {
	        if (isAnswerMapToQuestion(idQuestion, idAnswer))
	        	answerDAO.deleteById(idAnswer);
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

	@Override
	public Iterable<Answer> findAll() {
		
		return answerDAO.findAll();		
	}

	private boolean isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) throws NotFoundException {
        Optional<Answer> answer = answerDAO.findById(idAnswer);
        boolean isMapped=false;
        if (answer.isPresent())
        {
        	if (Objects.equals(idQuestion, answer.get().getQuestion().getId()))  
        		isMapped=true;
        	else  {
        		throw new NotFoundException ("Question with ID: '" + idQuestion + "' does not contain answer with ID: '" + idAnswer+ "'");}
        	}
        
		return isMapped;
        
    }
	


}

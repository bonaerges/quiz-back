package com.bonaerges.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.AnswerDAO;
import com.bonaerges.quizback.dao.QuestionDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger logger= LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	AnswerDAO answerDAO;
 
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
		//Delete answesr linked to question
		t.getAnswer().forEach(answer ->answerDAO.deleteById(answer.getId()));
		questionDAO.delete(t);
		logger.info(" Question-Asnwer delete successfully " + t.toString());
		
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
	//TO modify  the correct answer map to a question in case user need to change response
	public void updateSelectedAnswer(Question question, Answer answer) {
		Optional <Question> questionObject=questionDAO.findById(question.getId());
		if (questionObject.isPresent()) {
			questionObject.get().setCorrectAnswer(answer);
			questionDAO.save(questionObject.get());
		}
	}

	@Override
	public Optional<Question> findByDescription(String name) {
		Optional <Question> questionObject=questionDAO.findByDescription(name);
		logger.info(" Question findByDescription successfully " + questionObject.toString());
		return questionObject;
	}
	

	
}

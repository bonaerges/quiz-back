package com.bonaerges.quizback.service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.AnswerDAO;
import com.bonaerges.quizback.dao.QuestionDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {

	
	@Autowired
	AnswerDAO answerDAO;

	@Autowired
	QuestionDAO questionDAO;
	
	@Override
	public Answer create(Answer t) {
		Answer answerObject=answerDAO.save(t);
		log.info(" Answer create successfully " + t.toString());
		return answerObject;
	}

	@Override
	public void update(Answer t) {
		answerDAO.save(t);
		log.info(" Answer update successfully " + t.toString());
		
	}
	//Add answer to question if asnwer wasn not already linked to question
	public void addAnswerQuestion(Answer t,Integer idQuestion) {
		Optional<Question> question=questionDAO.findById(idQuestion);		
		Optional <Answer> answerLink=answerDAO.findById(t.getId());	
		if (answerLink.isPresent()) {
		 if (isAnswerMapToQuestion(idQuestion,answerLink.get().getId())) {
			 log.error ("Question with ID: '" + idQuestion + "' already map to answer with ID: '" + answerLink.get().getId()+ "'");
		 }
		 else {
			 answerLink.get().setQuestion(question.get());
			 answerDAO.save(answerLink.get());
			 log.info(" Add Answer to question successfully " + answerLink.get().toString());
		 }
		}
		else {
			 log.error ("Answer not found linked to question id " + idQuestion);
		}
		
	}
	
	//Link an update answer to previous existing question
	public void updatedAnswerQuestion(Answer t,Integer idAnswer,Integer idQuestion) {
	Optional<Question> question=questionDAO.findById(idQuestion);
	Optional <Answer> answerLink=answerDAO.findById(t.getId());	

	if (question.isPresent()) {
	 if (isAnswerMapToQuestion(idQuestion,idAnswer)) {
		 	answerLink.get().setDescription(t.getDescription());
		 	answerLink.get().setIsCorrect(t.getIsCorrect());
			answerDAO.save(answerLink.get());
			log.info(" Add Update Answer " + idAnswer + " to question " + idQuestion + " successfully " + answerLink.toString());
	 	}
	}	
	}
	@Override
	public void delete(Answer t) {
		answerDAO.delete(t);
		log.info(" Answer delete successfully " + t.toString());
		
	}
	
	//Delete and answer mapped to an existing question
	 public void deleteAnswerQuestion(Integer idQuestion, Integer idAnswer)  {
	        if (isAnswerMapToQuestion(idQuestion, idAnswer))
	        	answerDAO.deleteById(idAnswer);
	  }
	 
	@Override
	public Optional<Answer> findById(Integer id){	
		Optional <Answer> answerObject=answerDAO.findById(id);		
		log.info(" Answer findById successfully " + answerObject.toString());
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
		answerObject.ifPresent(a ->log.info("Answer findOneByDescriptionOrderByIdDesc "  + a.toString()));
		return answerObject;
		
	}

	@Override
	public Iterable<Answer> findAll() {
		
		return answerDAO.findAll();		
	}

	private boolean isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) {
        Optional<Answer> answer = answerDAO.findById(idAnswer);
        boolean isMapped=false;
        if (answer.isPresent())
        {
        	if (Objects.equals(idQuestion, answer.get().getQuestion().getId()))  
        		isMapped=true;
        	else  {
        		log.error ("Question with ID: '" + idQuestion + "' does not contain answer with ID: '" + idAnswer+ "'");}
        	}
        
		return isMapped;
        
    }
	


}

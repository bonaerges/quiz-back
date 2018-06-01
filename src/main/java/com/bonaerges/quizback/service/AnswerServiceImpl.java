package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.AnswerDAO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {

	
	@Autowired
	AnswerDAO answerDAO;

	@Autowired
	QuestionService questionService;
	
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
	public Answer addAnswerQuestion(Answer t,Integer idQuestion) {
		Optional<Question> question=questionService.findById(idQuestion);	
		Answer saveAnswer =null;
		if (question.isPresent()) {
			 t.setQuestion(question.get());
			 saveAnswer=create(t);
			 log.info(" Add Answer to question successfully " + t.toString());
		 }
		else {
			 log.error ("Not found question id " + idQuestion);
		}
		return saveAnswer;
	}
	
	//Link an update answer to previous existing question
	public void updatedAnswerQuestion(Answer t,Integer idAnswer,Integer idQuestion) {
	Optional<Question> question=questionService.findById(idQuestion);
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
	public List<Answer> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return (List<Answer>) answerDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());		
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
        	if (answer.get() != null && answer.get().getQuestion() != null) {
        		if (Objects.equals(idQuestion, answer.get().getQuestion().getId()))  
        			isMapped=true;
        		else  {
        			log.error ("Question with ID: '" + idQuestion + "' does not contain answer with ID: '" + idAnswer+ "'");
        		}
        	}
        	else {
        		log.error ("Answer with id " + idAnswer+ " not found");
        	}
        }
        
		return isMapped;
        
    }
	


}

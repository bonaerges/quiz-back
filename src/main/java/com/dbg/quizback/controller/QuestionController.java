package com.dbg.quizback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.question.QuestionMapper;
import com.dbg.quizback.dto.QuestionDTO;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.service.AnswerService;
import com.dbg.quizback.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	

	//question/id?page=X&size=X
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public QuestionDTO  findById(
			@PathVariable("id") Integer id){
		Optional<Question> question=questionService.findById(id);
		question.ifPresent(q-> log.info("Question found "));
		//load all answers linked to current question on DTO
		return questionMapper.modelToDto(question.get());
	}
	
	//question?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<QuestionDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Question> questions=questionService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll questions count is: "+ Integer.toString(questions.size()));
		return questionMapper.modelToDto(questions);
	}
	
	//question/ and POST METHOD with body
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)	
	public QuestionDTO create(@RequestBody QuestionDTO dto) {
		final Question question = questionMapper.dtoToModel(dto);
		final Question createQuestion = questionService.create(question);
		log.info("Question " + createQuestion.getId() + " succesfuly created.");
		log.warn("Pending to create answers linked to question");
		return questionMapper.modelToDto(createQuestion);
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody QuestionDTO dto) {
		
		Optional<Question> question=questionService.findById(id);
		ResponseEntity<?> respEnt=new ResponseEntity<>(HttpStatus.OK);
	    if(question.isPresent()) {
	    	questionService.update(questionMapper.dtoToModel(dto));  
	    }
	    else respEnt=new ResponseEntity(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestBody QuestionDTO dto) {		
		Optional<Question> question=questionService.findById(id);
		ResponseEntity respEnt=new ResponseEntity<Object>(HttpStatus.OK);
	    if(question.isPresent()) {
	    	//delete all answers linked to current question 
	    	question.get().getAnswer().forEach((final Answer answerLink)->answerService.delete(answerLink));
	    	questionService.delete(questionMapper.dtoToModel(dto));   
	    	log.info("Succesfully delete question " + id + " and answers linked to question"); 
	    }
	    else {
	    	respEnt=new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	    }
		return respEnt;
		
	}
}

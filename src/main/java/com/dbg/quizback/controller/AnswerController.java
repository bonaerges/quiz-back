package com.dbg.quizback.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.answer.AnswerMapper;
import com.dbg.quizback.dto.AnswerDTO;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.service.AnswerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/answer")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@Autowired
	AnswerMapper answerMapper;
	
	final ResponseEntity respEntOK=new ResponseEntity<Object>(HttpStatus.OK);
	final ResponseEntity respEntKO=new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	

	//answer/id/
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public AnswerDTO  findById(
			@PathVariable("id") Integer id){
		Optional<Answer> answer=answerService.findById(id);
		answer.ifPresent(q-> log.info("Answer found "));
		return answerMapper.modelToDto(answer.get());
	}
	
	//answer?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public Set<AnswerDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Answer> answers=answerService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll answers count is: "+ Integer.toString(answers.size()));
		return answerMapper.modelToDto(answers);
	}
	
	//answer/ and POST METHOD with body
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public AnswerDTO create(@RequestBody AnswerDTO dto) {
		final Answer answer = answerMapper.dtoToModel(dto);
		final Answer createAnswer = answerService.create(answer);
		log.info("Answer " + createAnswer.getId() + " succesfuly created.");
		return answerMapper.modelToDto(createAnswer);
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody AnswerDTO dto) {
		
		Optional<Answer> answer=answerService.findById(id);
		ResponseEntity<?> respEnt=new ResponseEntity<>(HttpStatus.OK);
	    if(answer.isPresent()) {
	    	answerService.update(answerMapper.dtoToModel(dto));  
	    }
	    else respEnt=new ResponseEntity(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestBody AnswerDTO dto) {		
		Optional<Answer> answer=answerService.findById(id);
		ResponseEntity<?> respEnt;
	     if(!answer.isPresent()) {
	    	 respEnt=this.respEntKO;	 
	    	 log.error("Error to deleted answer " + id + " not found");		    	
	     }
	     else {
	    	 	answerService.delete(answerMapper.dtoToModel(dto));
		    	log.info("Succesfully delete answer " + id + " and answers linked to answer");
		    	respEnt=this.respEntOK;
	     }
		return respEnt;
		
	}
	
}

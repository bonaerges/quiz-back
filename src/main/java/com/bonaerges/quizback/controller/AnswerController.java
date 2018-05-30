package com.bonaerges.quizback.controller;

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

import com.bonaerges.quizback.component.mapper.answer.AnswerMapper;
import com.bonaerges.quizback.dto.AnswerDTO;
import com.bonaerges.quizback.dto.AnswerUpdateDTO;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.service.AnswerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/answer")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@Autowired
	AnswerMapper answerMapper;
	
	final ResponseEntity<AnswerDTO> respEntOK=new ResponseEntity<AnswerDTO>(HttpStatus.OK);
	final ResponseEntity<AnswerDTO> respEntNotFound=new ResponseEntity<AnswerDTO>(HttpStatus.NOT_FOUND);
	

	/************************************HTTP METHOD GET *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<AnswerDTO>  findById(
			@PathVariable("id") Integer id){
		Optional<Answer> answerModel=answerService.findById(id);
		ResponseEntity<AnswerDTO> respEnt=respEntOK;
		if(answerModel.isPresent()) {
			log.info("Answer " + id + " found");
			respEnt=new ResponseEntity<AnswerDTO>(answerMapper.modelToDto(answerModel.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}
	//answer?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<AnswerDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Answer> answers=answerService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll answers count is: "+ Integer.toString(answers.size()));
		return answerMapper.modelToDto(answers);
	}
	
	/************************************HTTP METHOD POST *************************************/
	//answer/ and POST METHOD with body
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AnswerDTO> create(@RequestBody AnswerDTO dto) {
		ResponseEntity<AnswerDTO> respEnt;
		final Answer answer = answerMapper.dtoToModel(dto);
		final Answer createAnswer = answerService.create(answer);
		respEnt=new ResponseEntity<AnswerDTO>(dto,HttpStatus.CREATED);
		log.info("Answer " + createAnswer.getId() + " succesfuly created.");
		return respEnt;
	}

	/************************************HTTP METHOD PUT *************************************/
	
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<AnswerUpdateDTO> update(@PathVariable("id") Integer id, @RequestBody AnswerUpdateDTO dto) {
		
		Optional<Answer> answer=answerService.findById(id);
		ResponseEntity<AnswerUpdateDTO> respEnt=new ResponseEntity<AnswerUpdateDTO>(HttpStatus.OK);
	    if(answer.isPresent()) {
	    	answer.get().setDescription(dto.getDescription());
	    	answerService.update(answer.get());  
	    }
	    else respEnt=new ResponseEntity<AnswerUpdateDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	
	/************************************HTTP METHOD DELETE *************************************/

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<AnswerUpdateDTO> delete(@PathVariable("id") Integer id) {		
		Optional<Answer> answer=answerService.findById(id);
		ResponseEntity<AnswerUpdateDTO> respEnt;
	     if(!answer.isPresent()) {
	    	 respEnt=new ResponseEntity<AnswerUpdateDTO>(HttpStatus.NOT_FOUND);
	    	 log.error("Error to deleted answer " + id + " not found");		    	
	     }
	     else {
	    	 	answerService.delete(answer.get());
		    	log.info("Succesfully delete answer " + id + " and answers linked to answer");
		    	respEnt=new ResponseEntity<AnswerUpdateDTO>(HttpStatus.OK);
	     }
		return respEnt;
		
	}
	
}

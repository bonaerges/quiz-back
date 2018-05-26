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
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.questionnaire.QuestionnaireMapper;
import com.dbg.quizback.dto.QuestionnaireDTO;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.service.AnswerService;
import com.dbg.quizback.service.QuestionnaireService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/questionnairenaire")
public class QuestionnaireController {
	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	QuestionnaireMapper questionnaireMapper;
	
	

	//questionnaire/id?page=X&size=X
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public QuestionnaireDTO  findById(
			@PathVariable("id") Integer id){
		Optional<Questionnaire> questionnaire=questionnaireService.findById(id);
		questionnaire.ifPresent(q-> log.info("Questionnaire found "));
		return questionnaireMapper.modelToDto(questionnaire.get());
	}
	
	//questionnaire?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<QuestionnaireDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Questionnaire> questionnaires=questionnaireService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll questionnaires count is: "+ Integer.toString(questionnaires.size()));
		return questionnaireMapper.modelToDto(questionnaires);
	}
	
	//questionnaire/ and POST METHOD with body
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)	
	public QuestionnaireDTO create(@RequestBody QuestionnaireDTO dto) {
		final Questionnaire questionnaire = questionnaireMapper.dtoToModel(dto);
		final Questionnaire createQuestionnaire = questionnaireService.create(questionnaire);
		log.info("Questionnaire " + createQuestionnaire.getId() + " succesfuly created.");
		log.warn("Pending to create answers linked to questionnaire");
		return questionnaireMapper.modelToDto(createQuestionnaire);
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {
		
		Optional<Questionnaire> questionnaire=questionnaireService.findById(id);
		ResponseEntity<?> respEnt=new ResponseEntity<>(HttpStatus.OK);
	    if(questionnaire.isPresent()) {
	    	questionnaireService.update(questionnaireMapper.dtoToModel(dto));  
	    }
	    else respEnt=new ResponseEntity(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {		
		Optional<Questionnaire> questionnaire=questionnaireService.findById(id);
		ResponseEntity respEnt=new ResponseEntity<Object>(HttpStatus.OK);
	    if(questionnaire.isPresent()) {
	    	//delete all answers linked to current questionnaire 
	    //	questionnaire.get().getQuestion().forEach((final Question answerLink)->answerService.delete(answerLink));
	    	questionnaireService.delete(questionnaireMapper.dtoToModel(dto));   
	    	log.info("Succesfully delete questionnaire " + id + " and answers linked to questionnaire"); 
	    }
	    else {
	    	respEnt=new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	    }
		return respEnt;
		
	}
}

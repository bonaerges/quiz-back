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
import com.dbg.quizback.dto.AnswerDTO;
import com.dbg.quizback.dto.QuestionnaireDTO;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.model.Questionnaire;
import com.dbg.quizback.model.User;
import com.dbg.quizback.service.AnswerService;
import com.dbg.quizback.service.QuestionService;
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
	QuestionService questionService;
	
	@Autowired
	QuestionnaireMapper questionnaireMapper;
	
	
	final ResponseEntity<QuestionnaireDTO> respEntOK=new ResponseEntity<QuestionnaireDTO>(HttpStatus.OK);
	final ResponseEntity<QuestionnaireDTO> respEntNotFound=new ResponseEntity<QuestionnaireDTO>(HttpStatus.NOT_FOUND);
	

	/************************************HTTP METHOD GET *************************************/
	//questionnaire/id
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<QuestionnaireDTO>  findById(@PathVariable("id") Integer id)
	{
		Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt=respEntOK;
		if(questionnaireModel.isPresent()) {
			log.info("Questionnaire " + id + " found");
			respEnt=new ResponseEntity<QuestionnaireDTO>(questionnaireMapper.modelToDto(questionnaireModel.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}
	
	//questionnaire?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<QuestionnaireDTO>  findAll(
			@RequestParam(value = "page", defaultValue="1",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Questionnaire> questionnaires=questionnaireService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll questionnaires count is: "+ Integer.toString(questionnaires.size()));
		return questionnaireMapper.modelToDto(questionnaires);
	}
	
	/************************************HTTP METHOD POST *************************************/
	//questionnaire
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)	
	public QuestionnaireDTO create(@RequestBody QuestionnaireDTO dto) {
		final Questionnaire questionnaireModel = questionnaireMapper.dtoToModel(dto);
		final Questionnaire createQuestionnaire = questionnaireService.create(questionnaireModel);
		log.info("Questionnaire " + createQuestionnaire.getId() + " succesfuly created.");
		log.warn("Check answers linked to questionnaire");
		return questionnaireMapper.modelToDto(createQuestionnaire);
	}

	/************************************HTTP METHOD PUT *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<QuestionnaireDTO>  update(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {
		
		final Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt=respEntOK;
	    if(questionnaireModel.isPresent()) {
	    	questionnaireService.update(questionnaireMapper.dtoToModel(dto));  
	    }
	    else respEnt=respEntNotFound;
		return respEnt;
	}

	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<QuestionnaireDTO> delete(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {		
		final Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt=respEntOK;
	    if(questionnaireModel.isPresent()) {
	    	//delete all answers linked to current questionnaire 
	    	//questionnaireModel.get().getQuestion().forEach((final Question questionMap)->questionService.delete(questionMap));
	    	questionnaireService.delete(questionnaireMapper.dtoToModel(dto));   
	    	log.info("Succesfully delete questionnaire " + id + " and answers linked to questionnaire"); 
	    }
	    else respEnt=respEntNotFound;
		return respEnt;
		
	}
}

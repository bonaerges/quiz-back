package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;
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

import com.bonaerges.quizback.component.mapper.questionnaire.QuestionnaireMapper;
import com.bonaerges.quizback.dto.AnswerDTO;
import com.bonaerges.quizback.dto.QuestionnaireDTO;
import com.bonaerges.quizback.dto.QuestionnaireQADTO;
import com.bonaerges.quizback.dto.QuestionnaireUserAnswerDTO;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.service.QuestionnaireService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/questionnaire")
public class QuestionnaireController {
	
	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	QuestionnaireMapper questionnaireMapper;
	
	@Autowired
	QuestionnaireMapper questionMapper;
	
	final ResponseEntity<QuestionnaireDTO> respEntOK=new ResponseEntity<QuestionnaireDTO>(HttpStatus.OK);
	final ResponseEntity<QuestionnaireDTO> respEntNotFound=new ResponseEntity<QuestionnaireDTO>(HttpStatus.NOT_FOUND);
	

	/************************************HTTP METHOD GET *************************************/
	//questionnaire/id
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<QuestionnaireQADTO>  findById(@PathVariable("id") Integer id)
	{
		Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		ResponseEntity<QuestionnaireQADTO> respEnt=new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK) ;
		if(questionnaireModel.isPresent()) {
			QuestionnaireQADTO quizQ= new QuestionnaireQADTO();
			
			//quizQ.setQuestion(questoinMappe);
			log.info("Questionnaire " + id + " found");
			respEnt=new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK);
		}
		//else respEnt=";
		return respEnt;
	}
	
	//questionnaire?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public List<QuestionnaireDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		List<Questionnaire> questionnaires=questionnaireService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
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
	
	@ResponseBody	
	@RequestMapping(path="/{id}/saveAnswer",method = RequestMethod.PUT)
	public ResponseEntity<AnswerDTO>  saveAnswer(@PathVariable("user") Integer idUser,@PathVariable("id") Integer id,
			QuestionnaireUserAnswerDTO qUADTO){
		
//		Optional <Course>  courseUser=courseService.findById(id);
//		if (courseUser.isPresent()) {
//				Optional <User> user=userService.findById(idUser);
//				courseUser.get().getUser().add(user.get());
//				courseService.update(courseUser.get());
//				return new ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
//		}
//		else 
		return new ResponseEntity<AnswerDTO>(HttpStatus.NOT_FOUND);
			
		
	}

	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<QuestionnaireDTO> delete(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {		
		final Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt=respEntOK;
	    if(questionnaireModel.isPresent()) {
	    	questionnaireService.delete(questionnaireMapper.dtoToModel(dto));   
	    	log.info("Succesfully delete questionnaire " + id + " and answers linked to questionnaire"); 
	    }
	    else respEnt=respEntNotFound;
		return respEnt;
		
	}
}
